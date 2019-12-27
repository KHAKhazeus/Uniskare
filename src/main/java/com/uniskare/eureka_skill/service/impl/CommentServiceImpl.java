package com.uniskare.eureka_skill.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.entity.CommentPic;
import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.repository.CommentPicRepo;
import com.uniskare.eureka_skill.repository.CommentRepo;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniskare.eureka_skill.entity.Comment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static com.uniskare.eureka_skill.service.Helper.Const.*;

/**
 * @author : Bhy
 * @description ：
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    CommentPicRepo commentPicRepo;

    @Autowired
    SkillRepo skillRepo;

    @Override
    public BaseResponse insertComment(JSONObject comment) {
        try{
            String userId = comment.getString(USER_ID);
            int skillId = comment.getIntValue(SKILL_ID);

            List<Comment> comments = commentRepo.findBySkillId(skillId);
            Skill skill = skillRepo.findBySkillId(skillId);


            Timestamp timestamp = comment.getTimestamp(DATE);
            String content = comment.getString(CONTENT);
            JSONArray images = comment.getJSONArray(IMAGES);
            int score = comment.getIntValue(SCORE);
            Comment comment1 = new Comment();
            comment1.setContent(content);
            comment1.setSkillId(skillId);
            comment1.setUserId(userId);
            comment1.setScore(score);
            comment1.setTime(timestamp);


            commentRepo.save(comment1);

            CommentPic commentPic = new CommentPic();
            commentPic.setCommentId(comment1.getCommentId());
            for(int i = 0;i < images.size();i++){
                commentPic.setUrl(images.getString(i));
                commentPic.setPindex(i);
            }
            commentPicRepo.save(commentPic);


            BigDecimal cur_score = BigDecimal.valueOf((skill.getScore().doubleValue()*comments.size()+score)/(comments.size()+1));
            skill.setScore(cur_score);
            skillRepo.save(skill);

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
    public BaseResponse getCommentBySkillId(int skillId) {
        try{
            List<Comment> result = commentRepo.findBySkillId(skillId);
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
