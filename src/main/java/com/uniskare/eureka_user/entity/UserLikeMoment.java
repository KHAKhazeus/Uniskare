package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_like_moment", schema = "sedb", catalog = "")
@IdClass(UserLikeMomentPK.class)
public class UserLikeMoment {
    private int momentId;
    private String userId;

    @Id
    @Column(name = "moment_id")
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @Id
    @Column(name = "user_id")
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
