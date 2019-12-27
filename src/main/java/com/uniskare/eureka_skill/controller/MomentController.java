package com.uniskare.eureka_skill.controller;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.uniskare.eureka_skill.service.Helper.Const.USER_ID;

/**
 * @author : SCH001
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/comm") //基路径
public class MomentController {
    @Autowired
    MomentService momentService;

    //todo:点赞？
    @RequestMapping(value = "/moment/star_moment", method = RequestMethod.POST)
    public BaseResponse starMoment(@RequestBody JSONObject body) {
        return momentService.starMoment(body);
    }

    @RequestMapping(value = "moment/unstar_moment", method = RequestMethod.POST)
    public BaseResponse unstarMoment(@RequestBody JSONObject body)
    {
        return momentService.unstarMoment(body);
    }

    @RequestMapping(value = "/moment/insert", method = RequestMethod.POST)
    public BaseResponse insertMoment(@RequestBody JSONObject body) {
        //Moment
        return momentService.insertMoment(body);
    }

    @RequestMapping(value = "/moment/update", method = RequestMethod.POST)
    public BaseResponse updateMoment(@RequestBody JSONObject body) {
        //Moment
        return momentService.updateMoment(body);
    }

    @RequestMapping(value = "/moment/info/{momentId}", method = RequestMethod.GET)
    public  BaseResponse getMomentInfo(@PathVariable("momentId") int momentId){
        return momentService.getMomentInfo(momentId);
    }

    @RequestMapping(value = "/moment/all/{userId}", method = RequestMethod.GET)
    public BaseResponse getAllMoments(@PathVariable("userId") String watcher_id,
                                   @RequestParam("page") int page) {
//        @PathVariable("userId") String userId, @RequestParam("page") int page
        return momentService.getAllMoments(watcher_id, page);
    }

    @RequestMapping(value = "/moment/{userId}", method = RequestMethod.GET)
    public BaseResponse getOnesMoments(@PathVariable("userId") String userId,
                                   @RequestParam("page") int page,@RequestParam(USER_ID) String watcher_id)
    {
//        System.out.println(userId + " " + page + " " + watcher_id);
        return momentService.getOnesMoments(userId,page,watcher_id);
    }

    //这里不需要用户id吧==
    @RequestMapping(value = "/moment/delete/{userId}/{momentId}", method = RequestMethod.DELETE)
    public BaseResponse deleteMoment(@PathVariable("momentId") int momentId, @PathVariable("userId") String id) {
    //@PathVariable("momentId") int momentId, @PathVariable("userId") String id
        return momentService.deleteMoment(momentId);
    }


}