package com.uniskare.eureka_skill.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.repository.OrderRepo;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.service.Helper.Const;
import com.uniskare.eureka_skill.service.Helper.MyPageHelper;
import com.uniskare.eureka_skill.service.OrderService;
import com.uniskare.eureka_skill.dto.OrderDTO;
import com.uniskare.eureka_skill.dto.OrderPageDTO;
import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.entity.SkillOrder;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.repository.UserRepo;
import com.uniskare.eureka_skill.service.Helper.BackendException;
import com.uniskare.eureka_skill.service.impl.RMQ.RMQConfig;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import static com.uniskare.eureka_skill.service.Helper.Const.*;

/**
 * @author : SCH001
 * @description :
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    private UserRepo userRepo;

    //RMQ
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public JSONArray getAllOrders() {
        List<SkillOrder> orders = orderRepo.findAll();
        JSONArray jsonArray = new JSONArray();
        for (SkillOrder order : orders) {
            jsonArray.add(new JSONObject().fluentPut(Const.ORDER_STATUS, order.getState())
                    .fluentPut(Const.ORDER_ID, order.getOrderId())
                    .fluentPut(Const.ORDER_TIME, order.getOrderTime())
            );
        }
        return jsonArray;
    }

    @Override
    public BaseResponse getOrdersByState(JSONObject jsonObject) {

        try {
            String user_id = jsonObject.getString(Const.USER_ID);
            Byte order_state = jsonObject.getByte(Const.ORDER_STATUS);

            List<SkillOrder> skillOrders;
            if (order_state != -1) {
                //取对应状态的用户订单
                skillOrders = orderRepo.findAllByUserIdAndState(user_id, order_state);
            } else {
                //全都要
                skillOrders = orderRepo.findAllByUserId(user_id);
            }
            List<OrderDTO> jsonArray = new ArrayList<>();
            for (SkillOrder skillOrder : skillOrders) {
                Skill skill = skillRepo.findBySkillId(skillOrder.getSkillId());
                User user = userRepo.getOne(skill.getUserId());

                OrderDTO orderDTO = new OrderDTO(skill.getCover(), skillOrder.getValue(), skillOrder.getOrderTime(),
                        user.getUniNickName(), skill.getTitle());

                jsonArray.add(orderDTO);
            }

            //index & size
            //todo: 前端好像需要发页码
            //这里可以自己撸一个分页器
            //这里直接0
            Pageable pageable = PageRequest.of(0, 5);
            List<OrderDTO> dtoPage = MyPageHelper.convert2Page(jsonArray, pageable);
//            int start = (int)pageable.getOffset();
//            int end = Math.min((start + pageable.getPageSize()), jsonArray.size());
//            Page<OrderDTO> dtoPage = new PageImpl<OrderDTO>(
//                    jsonArray.subList(start, end), pageable, jsonArray.size()
//            );

            OrderPageDTO ret = new OrderPageDTO(dtoPage, NUM_PER_PAGE, (jsonArray.size() - 1) / NUM_PER_PAGE);


            return new BaseResponse(
                    null, Code.OK, Code.NO_ERROR_MESSAGE, ResponseMessage.QUERY_SUCCESS, null, ret
            );
        } catch (Exception e) {
            System.out.println(e.toString());
            return new BaseResponse(
                    null, Code.BAD_REQUEST, e.toString(), null, null, null
            );
        }
    }


    @Override
    //对订单进行操作，改变订单的操作
    public BaseResponse operateOrder(JSONObject json, Byte state) {
        try {
            int order_id = json.getIntValue(Const.ORDER_ID);
            SkillOrder skillOrder = orderRepo.getOne(order_id);

            // 不是debug状态哦
//            assert skillOrder.getState() + 1 == state;
            if (skillOrder.getState() + 1 != state) {
                //如果不是取消订单操作，那么订单的状态每次都会加1
                //如果是取消订单操作，那么订单一定不是完成（finished）状态
                if (!state.equals(ORDER_STATUS_CANCELED) || skillOrder.getState().equals(ORDER_STATUS_FINISHED))
                    throw new BackendException("Order State is not correct Or Order has been finished!");
            }
//            System.out.println(skillOrder.getState()+" "+ state);

            skillOrder.setState(state);
            orderRepo.save(skillOrder);
            return new BaseResponse(Code.OK, Code.NO_ERROR_MESSAGE, ResponseMessage.UPDATE_SUCCESS, null);
        } catch (Exception e) {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    public BaseResponse newOrder(JSONObject json) {
        try {
            int skill_id = json.getIntValue(Const.SKILL_ID);
            String user_id = json.getString(Const.USER_ID);
            //2019-12-17 10:30:10
            Timestamp order_time = json.getTimestamp(Const.ORDER_TIME);
            double val = json.getDouble(Const.ORDER_VALUE);

            SkillOrder skillOrder = new SkillOrder(skill_id, user_id, Const.ORDER_STATUS_PLACED,
                    order_time, val);

            skillOrder = orderRepo.save(skillOrder);
            //向RMQ队列发送订单号
            System.out.println(skillOrder.getOrderId());
            sendMsgToRMQ(skillOrder.getOrderId());

            return new BaseResponse(Code.OK, Code.NO_ERROR_MESSAGE,
                    ResponseMessage.INSERT_SUCCESS, null);
        } catch (Exception e) {
            return new BaseResponse(Code.NOT_ACCEPTABLE, e.toString(),
                    ResponseMessage.OPERATION_FAIL, null);
        }
    }


    //todo
    //新建订单时会发一条消息
    public void sendMsgToRMQ(int order_id) {
        // 通过广播模式发布延时消息 延时 持久化消息 消费后销毁 这里无需指定路由，会广播至每个绑定此交换机的队列
        rabbitTemplate.convertAndSend(RMQConfig.Delay_Exchange_Name, "", order_id, message -> {
            System.out.println("正在向RMQ发送请求 " + order_id);
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(ORDER_TIME_OUT);   // 毫秒为单位，指定此消息的延时时长
            return message;
        });
    }

    //消费者调用
    //判断是否已经付款
    //若未付款直接取消

    /**
     * @param order_id 要取消的订单id
     * @return 如果成功取消该订单或者该订单已经
     */
    public void handleOrderWhenTimeOut(int order_id) {
        SkillOrder order = orderRepo.findByOrderId(order_id);
        if (order == null) {
            System.out.println("未找到该订单，该订单可能已被取消！");
            return;
        }
        if (order.getState().equals(ORDER_STATUS_PLACED)) {
            orderRepo.delete(order);
            System.out.println("成功取消");
        } else {
            System.out.println("该订单已经支付");
        }
    }
}
