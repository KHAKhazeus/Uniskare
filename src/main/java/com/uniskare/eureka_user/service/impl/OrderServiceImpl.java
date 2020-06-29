package com.uniskare.eureka_user.service.impl;
// CHECKSTYLE:OFF
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.entity.SkillOrder;
import com.uniskare.eureka_user.repository.OrderRepo;
import com.uniskare.eureka_user.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.uniskare.eureka_user.service.Helper.Const.*;

/**
 * @author : SCH001
 * @description :
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

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
}
