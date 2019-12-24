package com.uniskare.eureka_skill.entity;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@AllArgsConstructor
public class UserLikeMomentPK implements Serializable {
    private int momentId;
    private String userId;

    @javax.persistence.Column(name = "moment_id")
    @javax.persistence.Id
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @javax.persistence.Column(name = "user_id")
    @javax.persistence.Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLikeMomentPK that = (UserLikeMomentPK) o;
        return momentId == that.momentId &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, userId);
    }
}
