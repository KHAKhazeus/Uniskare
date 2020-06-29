package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserLikeMomentPK implements Serializable {
    private int momentId;
    private String userId;

    @Column(name = "moment_id")
    @Id
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @Column(name = "user_id")
    @Id
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
