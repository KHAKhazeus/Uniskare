package com.uniskare.eureka_user.controller;

import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //跨域
@RequestMapping("/user") //基路径
public class DataController {
    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/data/{userId}",method = RequestMethod.GET)
    public BaseResponse getDataAnalysis(@PathVariable("userId") String userId){
        return dataService.getOnesData(userId);
    }

}
