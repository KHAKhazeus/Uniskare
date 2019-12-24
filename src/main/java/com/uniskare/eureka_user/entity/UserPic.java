package com.uniskare.eureka_user.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_pic", schema = "sedb", catalog = "")
@IdClass(UserPicPK.class)
public class UserPic {
    private String userId;
    private int picIndex;
    private String url;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "pic_index")
    public int getPicIndex() {
        return picIndex;
    }

    public void setPicIndex(int picIndex) {
        this.picIndex = picIndex;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPic userPic = (UserPic) o;
        return picIndex == userPic.picIndex &&
                Objects.equals(userId, userPic.userId) &&
                Objects.equals(url, userPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, picIndex, url);
    }
}
