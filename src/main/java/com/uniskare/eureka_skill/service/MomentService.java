package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;

/**
 * @author : SCH001
 * @description :
 */
public interface MomentService {
    public BaseResponse insertMoment(JSONObject body);
    public BaseResponse updateMoment(JSONObject body);
    public BaseResponse deleteMoment(JSONObject body);
    public BaseResponse getMoments(JSONObject body);
}
