package com.uniskare.eureka_skill.controller;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : SCH001
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/follow") //基路径
public class FollowController {
    @Autowired
    FollowService followService;

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public BaseResponse follow(@RequestBody JSONObject body)
    {
        return followService.follow(body);
    }


    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    public BaseResponse unfollow(@RequestBody JSONObject body)
    {
        return followService.unfollow(body);
    }

    @RequestMapping(value = "/following", method = RequestMethod.POST)
    public BaseResponse getFollowing(@RequestBody JSONObject body)
    {
        return followService.following(body);
    }

    @RequestMapping(value = "/followers", method = RequestMethod.POST)
    public BaseResponse getFollowers(@RequestBody JSONObject body)
    {
        return followService.followers(body);
    }
}
