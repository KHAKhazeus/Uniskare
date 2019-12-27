package com.uniskare.eureka_skill.controller;

/**
 * @author : SCH001
 * @description :
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.service.OrderService;
import com.uniskare.eureka_skill.service.impl.OrderServiceImpl;
import com.uniskare.eureka_skill.service.impl.RMQ.RMQConfig;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.uniskare.eureka_skill.service.Helper.Const.ORDER_ID;

@RestController
@CrossOrigin //跨域
@RequestMapping("/test") //基路径
public class TestController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/all")
    public JSONArray findAll(){return this.orderService.getAllOrders();}

    @RequestMapping(value="/rmq", method = RequestMethod.POST)
    public String hello(@RequestBody JSONObject body){
        int order_id = body.getIntValue(ORDER_ID);
        orderService.sendMsgToRMQ(order_id);
        return "Hello JUE CHEN";
    }
}