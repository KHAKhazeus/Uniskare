package com.uniskare.eureka_skill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.dto.OrderDTO;
import com.uniskare.eureka_skill.dto.RefundRequestDTO;
import com.uniskare.eureka_skill.entity.*;
import com.uniskare.eureka_skill.repository.*;
import com.uniskare.eureka_skill.service.Helper.BackendException;
import com.uniskare.eureka_skill.service.OrderService;
import com.uniskare.eureka_skill.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.uniskare.eureka_skill.controller.Response.BaseResponse.ErrResponse;
import static com.uniskare.eureka_skill.controller.Response.BaseResponse.SucResponse;
import static com.uniskare.eureka_skill.service.Helper.Const.*;

/**
 * @author : SCH001
 * @description :
 */
@Service
@Transactional
public class RefundServiceImpl implements RefundService {
    @Autowired
    RefundRepo refundRepo;
    @Autowired
    SkillRepo skillRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    RefundPicRepo rpRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    OrderService orderService;

    @Override
    public BaseResponse getRefundRequest() {
        List<Refund> refunds = refundRepo.findByStatus(SKILLER_DENIED);

        List<RefundRequestDTO> dtos = new ArrayList<>();
        try{
            for(Refund refund : refunds)
            {

                int refundId = refund.getRefundId();
                int orderId = refund.getOrderId();
                SkillOrder skillOrder = orderRepo.findByOrderId(orderId);

                Skill skill = skillRepo.findBySkillId(skillOrder.getSkillId());

                List<String> urls = getImages(refundId);

                User customer = userRepo.findByUniUuid(skillOrder.getUserId());

                RefundRequestDTO dto = new RefundRequestDTO(skill.getTitle(),
                        refund.getRefundId(),refund.getReason(),urls,
                        customer.getUniAvatarUrl(),customer.getUniNickName()
                );
                dtos.add(dto);
            }
            return SucResponse(dtos);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

    @Override
    @Modifying
    @Transactional
    public BaseResponse AgreeRefund(JSONObject body) {
        int refundId = body.getIntValue(REFUND_ID);

        try {
            Refund refund = refundRepo.findByRefundId(refundId);
            int order_id = refund.getOrderId();
//            SkillOrder order = orderRepo.findByOrderId(order_id);

            //删除退款Item
            refundRepo.deleteByRefundId(refundId);
            //外键约束，先删除Refund
            orderService.changeOrderState(order_id, ORDER_STATUS_CANCELED);

            return SucResponse(null);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse SkillerReject(JSONObject body) {
        int refundId = body.getIntValue(REFUND_ID);

        try {
            Refund refund = refundRepo.findByRefundId(refundId);
            refund.setStatus(SKILLER_DENIED);

            return SucResponse(null);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

    @Override
    @Modifying
    @Transactional
//    删除相关退款表项，更改订单状态为 ORDER_STATUS_TAKEN
    public BaseResponse WorkerReject(JSONObject body) {
        int refundId = body.getIntValue(REFUND_ID);

        try {
            Refund refund = refundRepo.findByRefundId(refundId);
            int orderId = refund.getOrderId();
            //删除 退款
            refundRepo.deleteByRefundId(refundId);
            //修改订单状态
            SkillOrder order = orderRepo.getOne(orderId);
            order.setState(ORDER_STATUS_TAKEN);

            return SucResponse(null);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse getSkillerOrder(JSONObject body) {
        String skiller_id = body.getString(USER_ID);
        //Order -> Skill -> User
        //获取所有订单，再做过滤
        List<SkillOrder> orders = orderRepo.findAllByState(ORDER_STATUS_TAKEN);
        orders.addAll(orderRepo.findAllByState(ORDER_STATUS_CONFIRMED));
        orders.addAll(orderRepo.findAllByState(ORDER_STATUS_FINISHED));
        orders.addAll(orderRepo.findAllByState(ORDER_STATUS_CANCELED));

        List<OrderDTO> dtos = new ArrayList<>();
        try {
            for(SkillOrder order:orders)
            {
//                //如果不是这个技客的
//                if(skiller_id.equals(order.getUserId()))
//                {
//                    continue;
//                }
                int skill_id = order.getSkillId();
                Skill skill = skillRepo.findBySkillId(skill_id);

                //当前技能对应的   **技客**
                String skiller_id1 = skill.getUserId();
                if(!skiller_id.equals(skiller_id1))
                {
                    continue;
                }

                User user = userRepo.findByUniUuid(order.getUserId());

                OrderDTO dto = new OrderDTO(skill,user,order);

                dtos.add(dto);
            }

            return SucResponse(dtos);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

    //helper
    private List<String> getImages(int refundId)
    {
        List<RefundPic> rePics =  rpRepo.findByRefundId(refundId);
        rePics.sort(Comparator.comparingInt(RefundPic::getPindex));//按照索引排序

        List<String> urls = new ArrayList<>();
        for(RefundPic refundPic : rePics)
        {
            urls.add(refundPic.getUrl());
        }
        return urls;
    }
}
