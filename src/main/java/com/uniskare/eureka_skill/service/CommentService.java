package com.uniskare.eureka_skill.service;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;

import com.uniskare.eureka_skill.entity.Comment;
/**
 * @author : Bhy
 * @description ：
 */
public interface CommentService {
    public BaseResponse insertComment(Comment comment);
    public BaseResponse getCommentBySkillId(int skillId);
}
