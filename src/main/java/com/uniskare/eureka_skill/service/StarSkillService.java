package com.uniskare.eureka_skill.service;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;

/**
 * @author : Bhy
 * @description ：
 */
public interface StarSkillService {
    public BaseResponse starSkill(String userId,int skillId);
    public BaseResponse unstarSkill(String userId,int skillId);
    public BaseResponse getStarSkill(int page,String userId);

}
