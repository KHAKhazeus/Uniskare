package com.uniskare.eureka_skill.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
@Table(name = "moment_pic", schema = "sedb", catalog = "")
@IdClass(MomentPicPK.class)
public class MomentPic {
    private int momentId;
    private int pindex;
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
        MomentPic momentPic = (MomentPic) o;
        return momentId == momentPic.momentId &&
                pindex == momentPic.pindex &&
                Objects.equals(url, momentPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, pindex, url);
    }
}
