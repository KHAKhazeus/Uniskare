package com.uniskare.eureka_user.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "moment_pic", schema = "sedb", catalog = "")
@IdClass(MomentPicPK.class)
public class MomentPic {
    private int momentId;
    private int index;
    private String url;

    @Id
    @Column(name = "moment_id")
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @Id
    @Column(name = "index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
        MomentPic momentPic = (MomentPic) o;
        return momentId == momentPic.momentId &&
                index == momentPic.index &&
                Objects.equals(url, momentPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, index, url);
    }
}
