package com.uniskare.eureka_skill.controller;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : SCH001
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/refund") //基路径
public class RefundController {
    @Autowired
    RefundService refundService;

    //技客拒绝退款的item
//    获得所有技客拒绝退款的退款申请信息
    @RequestMapping(value = "/rejected_refunds", method = RequestMethod.GET)
    public BaseResponse getRejectedRefunds()
    {
        return refundService.getRefundRequest();
    }


    //技客或者工作人员同意退款
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    public BaseResponse agreeRefund(JSONObject body)
    {
        return refundService.AgreeRefund(body);
    }

//    技客拒绝退款
    @RequestMapping(value = "/skiller_reject", method = RequestMethod.POST)
    public BaseResponse skillerReject(JSONObject body)
    {
        return refundService.SkillerReject(body);
    }

//    工作人员拒绝退款
    @RequestMapping(value = "/worker_reject", method = RequestMethod.POST)
    public BaseResponse workerReject(JSONObject body)
    {
        return refundService.WorkerReject(body);
    }

//    获得某个技客的接单信息
//
//行为: (只返回  ORDER_STATUS_TAKEN，ORDER_STATUS_CONFIRMED，
// ORDER_STATUS_FINISHED，ORDER_STATUS_CANCELED 这四个状态的
    @RequestMapping(value = "/skiller_order", method = RequestMethod.POST)
    public BaseResponse getSkillerOrder(JSONObject body)
    {
        return refundService.getSkillerOrder(body);
    }

}
