package com.uniskare.eureka_skill.service.impl;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.entity.StarSkill;
import com.uniskare.eureka_skill.repository.StarSkillRepo;
import com.uniskare.eureka_skill.service.StarSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author : Bhy
 * @description ：
 */
@Service
public class StarSkillServiceImpl implements StarSkillService {
    @Autowired
    StarSkillRepo starSkillRepo;
    private int pageSize = 10;
    @Override
    public BaseResponse starSkill(String userId, int skillId) {
        try {
            StarSkill starSkill = new StarSkill();
            starSkill.setSkillId(skillId);
            starSkill.setStarId(userId);
            starSkillRepo.save(starSkill);
            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.INSERT_SUCCESS
                    , "/"
                    , null);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    @Override
    public BaseResponse unstarSkill(String userId, int skillId) {
        try {
            StarSkill starSkill = new StarSkill();
            starSkill.setSkillId(skillId);
            starSkill.setStarId(userId);
            starSkillRepo.delete(starSkill);
            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.DELETE_SUCCESS
                    , "/"
                    , null);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    @Override
    public BaseResponse getStarSkill(int page, String userId) {
        try {
            Page<StarSkill> result = starSkillRepo.findByStarId(userId, PageRequest.of(page,pageSize));
            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.QUERY_SUCCESS
                    , "/"
                    , result);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }
}
