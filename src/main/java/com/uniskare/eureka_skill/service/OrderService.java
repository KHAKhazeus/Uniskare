package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;

public interface OrderService {
    //测试接口
    public JSONArray getAllOrders();

    //根据状态和用户id获取
    public BaseResponse getOrdersByState(JSONObject jsonObject);


//    public boolean changeOrderState(JSONObject json, Byte state);

    public BaseResponse operateOrder(JSONObject json, Byte state);

    public BaseResponse newOrder(JSONObject json);

    public void handleOrderWhenTimeOut(int order_id);
}
