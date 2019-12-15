package com.uniskare.eureka_skill.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.dto.OrderDTO;
import com.uniskare.eureka_skill.entity.Message;
import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.entity.SkillOrder;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.repository.OrderRepo;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.repository.UserRepo;
import com.uniskare.eureka_skill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public JSONArray getAllOrders()
    {
        List<SkillOrder> orders = orderRepo.findAll();
        JSONArray jsonArray = new JSONArray();
        for(SkillOrder order:orders)
        {
            jsonArray.add(new JSONObject().fluentPut(ORDER_STATUS, order.getState())
                    .fluentPut(ORDER_ID, order.getOrderId())
                    .fluentPut(ORDER_TIME, order.getOrderTime())
            );
        }
        return jsonArray;
    }

    @Override
    public BaseResponse getOrdersByState(JSONObject jsonObject)
    {
        try{
            String user_id = jsonObject.getString(USER_ID);
            Byte order_state = jsonObject.getByte(ORDER_STATUS);

            List<SkillOrder> skillOrders = orderRepo.findAllByUserIdAndState(user_id, order_state);
            JSONArray jsonArray = new JSONArray();
            for(SkillOrder skillOrder: skillOrders)
            {
                Skill skill = skillRepo.findBySkillId(skillOrder.getSkillId());
                User user = userRepo.getOne(skill.getUserId());

                OrderDTO orderDTO = new OrderDTO(skill.getCover(),skill.getPrice(),skillOrder.getOrderTime(),
                        user.getUniNickName(),skill.getTitle());

                jsonArray.add(orderDTO);
            }
            return new BaseResponse(
                    null, Code.OK, Code.NO_ERROR_MESSAGE, ResponseMessage.QUERY_SUCCESS,null,jsonArray
            );
        }catch (Exception e){
            System.out.println(e.toString());
            return new BaseResponse(
                    null, Code.BAD_REQUEST, e.toString(),null, null, null
            );
        }
    }


    @Override
    //对订单进行操作，改变订单的操作
    public BaseResponse operateOrder(JSONObject json, Byte state)
    {
        try{
            int order_id = json.getIntValue(ORDER_ID);
            SkillOrder skillOrder = orderRepo.getOne(order_id);

            assert skillOrder.getState() + 1 == state;

            skillOrder.setState(state);
            orderRepo.save(skillOrder);
            return new BaseResponse(Code.OK, Code.NO_ERROR_MESSAGE, ResponseMessage.UPDATE_SUCCESS, null);
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }
}
