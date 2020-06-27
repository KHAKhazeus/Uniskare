package com.uniskare.eureka_user.service;


import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;

public interface UserService {
    public BaseResponse register(String open_id);
    public BaseResponse login(JSONObject json);
    public BaseResponse getUserInfo(String id);
    public BaseResponse updateUserInfo(JSONObject json);
    public BaseResponse loginWithUpdate(JSONObject jsonObject,String id);
    public Boolean upLoadStatus(String id,int status,String school);
}
