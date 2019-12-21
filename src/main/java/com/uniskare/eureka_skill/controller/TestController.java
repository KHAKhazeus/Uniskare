package com.uniskare.eureka_skill.controller;

/**
 * @author : SCH001
 * @description :
 */
import com.alibaba.fastjson.JSONArray;
import com.uniskare.eureka_skill.service.OrderService;
import com.uniskare.eureka_skill.service.impl.RMQ.RMQConfig;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin //跨域
@RequestMapping("/test") //基路径
public class TestController {

    @Autowired
    private OrderService orderService;
    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/all")
    public JSONArray findAll(){return this.orderService.getAllOrders();}

    @GetMapping("/hello")
    public String hello(){
        // 通过广播模式发布延时消息 延时30分钟 持久化消息 消费后销毁 这里无需指定路由，会广播至每个绑定此交换机的队列
        rabbitTemplate.convertAndSend(RMQConfig.Delay_Exchange_Name, "","jue chen", message ->{
            System.out.println("Send out: " + message);
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay((10*1000));   // 毫秒为单位，指定此消息的延时时长
            return message;
        });
        return "Hello JUE CHEN";
    }
}