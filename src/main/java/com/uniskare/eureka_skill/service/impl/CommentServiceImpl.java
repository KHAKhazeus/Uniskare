package com.uniskare.eureka_skill.service.impl;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.repository.CommentRepo;
import com.uniskare.eureka_skill.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniskare.eureka_skill.entity.Comment;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author : Bhy
 * @description ：
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo commentRepo;
    @Override
    public BaseResponse insertComment(Comment comment) {
        try{
            commentRepo.save(comment);
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
