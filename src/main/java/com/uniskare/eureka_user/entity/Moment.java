package com.uniskare.eureka_user.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Moment {
    private int momentId;
    private String userId;
    private String content;
    private Byte canSee;
    private Timestamp time;

    @Id
    @Column(name = "moment_id")
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "can_see")
    public Byte getCanSee() {
        return canSee;
    }

    public void setCanSee(Byte canSee) {
        this.canSee = canSee;
    }

    @Basic
    @Column(name = "time")
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
