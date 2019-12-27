package com.uniskare.eureka_skill.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MomentPicPK implements Serializable {
    private int momentId;
    private int pindex;

    @Column(name = "moment_id")
    @Id
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @Column(name = "pindex")
    @Id
    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MomentPicPK that = (MomentPicPK) o;
        return momentId == that.momentId &&
                pindex == that.pindex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, pindex);
    }
}
