package com.uniskare.eureka_skill.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.uniskare.eureka_skill.entity.Comment;
import com.uniskare.eureka_skill.entity.Skill;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Bhy
 * @description ：
 */


public class SkillDetailDTO {
    private Skill skill;
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
