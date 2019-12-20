package com.uniskare.eureka_skill.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
public class Skill {
    private int skillId;
    private String userId;
    private String cover;
    private String video;
    private String title;
    private String content;
    private BigDecimal price;
    private String unit;
    private Integer model;
    private String fullType;
    private String subtype;
    private String subsubtype;
    private BigDecimal score;
    private Timestamp date;


    private User user;

    @ManyToOne
    @JoinColumn(name ="user_id",referencedColumnName="uni_uuid", insertable=false, updatable=false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {this.user = user;}

    public String getUserAvatar() {
        if(user != null) {
            return user.getUniAvatarUrl();
        }
        else {
            return null;
        }
    }
    public void setUserAvatar(User user) {

    }
    @javax.persistence.Id
    @javax.persistence.Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
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
    @javax.persistence.Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "video")
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @javax.persistence.Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "model")
    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "full_type")
    public String getFullType() {
        return fullType;
    }

    public void setFullType(String fullType) {
        this.fullType = fullType;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "subtype")
    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "subsubtype")
    public String getSubsubtype() {
        return subsubtype;
    }

    public void setSubsubtype(String subsubtype) {
        this.subsubtype = subsubtype;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "score")
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "date")
    public Timestamp getDate() {
        return date;
    }


    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return skillId == skill.skillId &&
//                Objects.equals(userId, skill.userId) &&
                Objects.equals(cover, skill.cover) &&
                Objects.equals(video, skill.video) &&
                Objects.equals(title, skill.title) &&
                Objects.equals(content, skill.content) &&
                Objects.equals(price, skill.price) &&
                Objects.equals(unit, skill.unit) &&
                Objects.equals(model, skill.model) &&
                Objects.equals(fullType, skill.fullType) &&
                Objects.equals(subtype, skill.subtype) &&
                Objects.equals(subsubtype, skill.subsubtype) &&
                Objects.equals(score, skill.score) &&
                Objects.equals(date, skill.date);
    }

    //去userId
    @Override
    public int hashCode() {
        return Objects.hash(skillId,  cover, video, title, content, price, unit, model, fullType, subtype, subsubtype, score, date);
    }
}
