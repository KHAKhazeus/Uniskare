package com.uniskare.eureka_skill.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.uniskare.eureka_skill.service.Helper.Const.*;

/**
 * @author : SCH001
 * @description :
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/orders") //基路径
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/orders_info", method = RequestMethod.POST)
    public BaseResponse getOrdersInfo(@RequestBody JSONObject body)
    {
        return orderService.getOrdersByState(body);
    }

//    用户下单？创建一个订单
    @RequestMapping(value = "/new_order", method = RequestMethod.POST)
    public BaseResponse newOrder(@RequestBody JSONObject body)
    {
        return orderService.newOrder(body);
    }

//    用户付款
    @RequestMapping(value="/pay", method = RequestMethod.POST)
    public BaseResponse payOrder(@RequestBody JSONObject body)
    {
        return orderService.operateOrder(body, ORDER_STATUS_PAID);
    }

//    用户确认
    @RequestMapping(value="/confirm", method = RequestMethod.POST)
    public BaseResponse confirmOrder(@RequestBody JSONObject body)
    {
        return orderService.operateOrder(body, ORDER_STATUS_CONFIRMED);
    }

    //技客接单
    @RequestMapping(value="/take_order", method = RequestMethod.POST)
    public BaseResponse takeOrder(@RequestBody JSONObject body)
    {
        return orderService.operateOrder(body, ORDER_STATUS_TAKEN);
    }

    //用户取消订单
    @RequestMapping(value="/cancel", method = RequestMethod.POST)
    public BaseResponse cancelOrder(@RequestBody JSONObject body)
    {
        return orderService.operateOrder(body, ORDER_STATUS_CANCELED);
    }

    //用户评论订单后 订单结束
    @RequestMapping(value="/review", method = RequestMethod.POST)
    public BaseResponse reviewOrder(@RequestBody JSONObject body)
    {
        return orderService.operateOrder(body, ORDER_STATUS_FINISHED);
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public BaseResponse applyRefund(@RequestBody JSONObject jsonObject){
        return orderService.applyRefund(jsonObject);
    }

    @RequestMapping(value = "/{userId}/refund_request",method = RequestMethod.GET)
    public BaseResponse getRefund(@PathVariable("userId") String userId){

    }

    @RequestMapping(value = "/{userId}/applu",method = RequestMethod.GET)
    public BaseResponse getApply(@PathVariable("userId") String userId){

    }

    @RequestMapping(value = "/{refundId}/info",method = RequestMethod.GET)
    public BaseResponse getRefundInfo(@PathVariable("refundId") int refundId){

    }

}