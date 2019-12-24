package com.uniskare.eureka_user.service;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;

public interface CertificationService {
    public BaseResponse upLoadCertification(JSONObject jsonObject);

    public BaseResponse getCertificatio();

    public BaseResponse changeState(String id,int state);

}
