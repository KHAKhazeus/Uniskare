package com.uniskare.eureka_skill.controller;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author : SCH001
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/moment") //基路径
public class MomentController {
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResponse insertMoment(@RequestBody JSONObject body) {
        //Moment
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse updateMoment(@RequestBody JSONObject body) {
        //Moment
        return null;
    }

    @RequestMapping(value = "/all/{userId}", method = RequestMethod.GET)
    public BaseResponse getMoments(@RequestBody JSONObject body) {
//        @PathVariable("userId") String userId, @RequestParam("page") int page
        return null;
    }

    @RequestMapping(value = "/delete/{userId}/{momentId}", method = RequestMethod.DELETE)
    public BaseResponse deleteMoment(@RequestBody JSONObject body) {
    //@PathVariable("momentId") int momentId, @PathVariable("userId") String id
        return null;
    }
}