package com.uniskare.eureka_skill.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
public class Comment {
    private int commentId;
    private String userId;
    private Integer skillId;
    private Integer score;
    private String content;
    private Timestamp time;

    private User user;

    private List<CommentPic> commentPics = new ArrayList<>();

    //只在返回技能详情的一个接口用到，comment不能用此方法，缺少信息
    @OneToMany
    @JoinColumn(name = "skill_id")
    public List<CommentPic> getCommentPics() {
        return commentPics;
    }
    public void setCommentPics(List<CommentPic> commentPics) {
        this.commentPics = this.commentPics;
    }



    @ManyToOne
    @JoinColumn(name ="user_id",referencedColumnName="uni_uuid", insertable=false, updatable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {this.user = user;}


    @javax.persistence.Id
    @javax.persistence.Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "skill_id")
    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(skillId, comment.skillId) &&
                Objects.equals(score, comment.score) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(time, comment.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId, skillId, score, content, time);
    }
}
