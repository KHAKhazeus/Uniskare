package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.entity.Skill;

/**
 * @author : Bhy
 * @description ：
 */
public interface SkillService {
    public BaseResponse save(JSONObject skill);
    public BaseResponse findAll(int page);
    public BaseResponse findById(int skillId);
    public BaseResponse deleteById(int skillId);
    public BaseResponse updateSkill(Skill skill);
    public BaseResponse findByFullType(String fullType, int page);
    public BaseResponse findByFullTypeAndSubtype(String fullType, String subtype, int page);
    public BaseResponse searchSkillByTitle(String title,int page);
    public BaseResponse findByUserId(String userId, int page);
}
