package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;

/**
 * @author : SCH001
 * @description :
 */
public interface RefundService {
    //获得所有技客拒绝退款的 退款申请信息(status = 1)
    public BaseResponse getRefundRequest();

    //技客同意退款
    //技客或者 工作人员同意退款
    //删除退款item，订单状态变为已经取消
    public BaseResponse AgreeRefund(JSONObject body);

    //技客拒绝退款
    public BaseResponse SkillerReject(JSONObject body);

    //工作人员拒绝退款
    public BaseResponse WorkerReject(JSONObject body);

    //获得某个技客的接单信息
    //user_id
//    (只返回  ORDER_STATUS_TAKEN，ORDER_STATUS_CONFIRMED，ORDER_STATUS_FINISHED，
//    ORDER_STATUS_CANCELED 这四个状态的订单信息)
    public BaseResponse getSkillerOrder(JSONObject body);
}
