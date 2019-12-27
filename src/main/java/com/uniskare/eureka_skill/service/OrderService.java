package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.service.Helper.BackendException;

public interface OrderService {
    //测试接口
    public JSONArray getAllOrders();

    //根据状态和用户id获取
    public BaseResponse getOrdersByState(JSONObject jsonObject);


    public BaseResponse operateOrder(JSONObject json, Byte state);

    void changeOrderState(int order_id, Byte state) throws BackendException;

    public BaseResponse newOrder(JSONObject json);

    public void handleOrderWhenTimeOut(int order_id);

    public BaseResponse applyRefund(JSONObject jsonObject);

    public BaseResponse getOrderRequestDTOs(String userId,int page, Boolean isRefund);

    public BaseResponse getRefundInfo(int refundId);
}
