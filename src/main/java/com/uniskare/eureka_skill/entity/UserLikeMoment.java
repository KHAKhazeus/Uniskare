package com.uniskare.eureka_skill.entity;

import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "user_like_moment", schema = "sedb", catalog = "")
@javax.persistence.IdClass(UserLikeMomentPK.class)
public class UserLikeMoment {
    private int momentId;
    private String userId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "moment_id")
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "user_id")
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
        UserLikeMoment that = (UserLikeMoment) o;
        return momentId == that.momentId &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, userId);
    }
}
