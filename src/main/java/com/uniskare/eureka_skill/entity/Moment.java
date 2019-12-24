package com.uniskare.eureka_skill.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
@AllArgsConstructor
@NoArgsConstructor
public class Moment {
    private int momentId;
    private String userId;
    private String content;
    private Byte canSee;
    private Timestamp time;


    public Moment(String userId, String content, Byte canSee, Timestamp time) {
        this.userId = userId;
        this.content = content;
        this.canSee = canSee;
        this.time = time;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "moment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
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
    @javax.persistence.Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "can_see")
    public Byte getCanSee() {
        return canSee;
    }

    public void setCanSee(Byte canSee) {
        this.canSee = canSee;
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
        Moment moment = (Moment) o;
        return momentId == moment.momentId &&
                Objects.equals(userId, moment.userId) &&
                Objects.equals(content, moment.content) &&
                Objects.equals(canSee, moment.canSee) &&
                Objects.equals(time, moment.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, userId, content, canSee, time);
    }
}
