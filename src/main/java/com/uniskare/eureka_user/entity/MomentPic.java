package com.uniskare.eureka_user.entity;

import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "moment_pic", schema = "sedb", catalog = "")
@javax.persistence.IdClass(MomentPicPK.class)
public class MomentPic {
    private int momentId;
    private int index;
    private String url;

    @javax.persistence.Id
    @javax.persistence.Column(name = "moment_id")
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "url")
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
