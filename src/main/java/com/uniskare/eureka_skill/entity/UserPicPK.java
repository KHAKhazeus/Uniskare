package com.uniskare.eureka_skill.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserPicPK implements Serializable {
    private String userId;
    private int picIndex;

    @Column(name = "user_id")
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "pic_index")
    @Id
    public int getPicIndex() {
        return picIndex;
    }

    public void setPicIndex(int picIndex) {
        this.picIndex = picIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPicPK userPicPK = (UserPicPK) o;
        return picIndex == userPicPK.picIndex &&
                Objects.equals(userId, userPicPK.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, picIndex);
    }
}
