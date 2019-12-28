package com.uniskare.eureka_user.service;

import com.uniskare.eureka_user.controller.Response.BaseResponse;

public interface DataService {
    BaseResponse getOnesData(String userId);

}
