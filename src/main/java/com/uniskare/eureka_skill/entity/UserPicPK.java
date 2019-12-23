package com.uniskare.eureka_skill.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
public class UserPicPK implements Serializable {
    private int userId;
    private int index;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "index")
    @Id
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPicPK userPicPK = (UserPicPK) o;
        return userId == userPicPK.userId &&
                index == userPicPK.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, index);
    }
}
