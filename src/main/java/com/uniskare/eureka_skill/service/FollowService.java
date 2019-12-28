package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;

/**
 * @author : SCH001
 * @description :
 */
public interface FollowService {
    BaseResponse follow(JSONObject body);
    BaseResponse unfollow(JSONObject body);

    BaseResponse followers(JSONObject body);
    BaseResponse following(JSONObject body);
}
