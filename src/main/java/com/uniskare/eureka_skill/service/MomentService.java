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

    public BaseResponse getOnesMoments(String user_id, int page, String watcher_id);
    public BaseResponse getAllMoments(String watcher_id, int page);

    public BaseResponse deleteMoment(int mom_id);

    public BaseResponse starMoment(JSONObject body);
}
