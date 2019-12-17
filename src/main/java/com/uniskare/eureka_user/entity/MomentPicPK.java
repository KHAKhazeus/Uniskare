package com.uniskare.eureka_user.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
public class MomentPicPK implements Serializable {
    private int momentId;
    private int index;

    @javax.persistence.Column(name = "moment_id")
    @javax.persistence.Id
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    @javax.persistence.Column(name = "index")
    @javax.persistence.Id
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
        MomentPicPK that = (MomentPicPK) o;
        return momentId == that.momentId &&
                index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, index);
    }
}
