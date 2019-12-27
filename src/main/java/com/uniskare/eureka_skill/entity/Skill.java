package com.uniskare.eureka_skill.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
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
    private Byte status;
    private BigDecimal score;
    private Timestamp date;

    @Id
    @Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "video")
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "model")
    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    @Basic
    @Column(name = "full_type")
    public String getFullType() {
        return fullType;
    }

    public void setFullType(String fullType) {
        this.fullType = fullType;
    }

    @Basic
    @Column(name = "subtype")
    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    @Basic
    @Column(name = "subsubtype")
    public String getSubsubtype() {
        return subsubtype;
    }

    public void setSubsubtype(String subsubtype) {
        this.subsubtype = subsubtype;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "score")
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Basic
    @Column(name = "date")
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
                Objects.equals(userId, skill.userId) &&
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
                Objects.equals(status, skill.status) &&
                Objects.equals(score, skill.score) &&
                Objects.equals(date, skill.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, userId, cover, video, title, content, price, unit, model, fullType, subtype, subsubtype, status, score, date);
    }
}
