package com.uniskare.eureka_skill.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
@Table(name = "user_pic", schema = "sedb", catalog = "")
@IdClass(UserPicPK.class)
public class UserPic {
    private int userId;
    private int pindex;
    private String url;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "pindex")
    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
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
        return userId == userPic.userId &&
                pindex == userPic.pindex &&
                Objects.equals(url, userPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, pindex, url);
    }
}
