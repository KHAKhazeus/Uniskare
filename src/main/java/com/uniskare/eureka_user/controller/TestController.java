package com.uniskare.eureka_user.controller;

/**
 * @author : SCH001
 * @description :
 */
import com.alibaba.fastjson.JSONArray;
import com.uniskare.eureka_user.service.OrderService;
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


    @GetMapping("/all")
    public JSONArray findAll(){return this.orderService.getAllOrders();}

    @GetMapping("/hello")
    public String hello(){
        return "Hello JUE CHEN";
    }
}