package com.uniskare.eureka_skill.service;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;

public interface RelationService {
    BaseResponse checkExistRelation(String followId,String fanId);
}
