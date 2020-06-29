package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MomentPicPK implements Serializable {
    private int momentId;
    private int index;

    @Column(name = "moment_id")
    @Id
    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
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
        MomentPicPK that = (MomentPicPK) o;
        return momentId == that.momentId &&
                index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(momentId, index);
    }
}
