package com.uniskare.eureka_skill.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RefundPicPK implements Serializable {
    private int refundId;
    private int pindex;

    @Column(name = "refund_id")
    @Id
    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
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
        RefundPicPK that = (RefundPicPK) o;
        return refundId == that.refundId &&
                pindex == that.pindex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(refundId, pindex);
    }
}
