package com.uniskare.eureka_skill.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
public class RefundPicPK implements Serializable {
    private int refundId;
    private int index;

    @Column(name = "refund_id")
    @Id
    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
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
        RefundPicPK that = (RefundPicPK) o;
        return refundId == that.refundId &&
                index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(refundId, index);
    }
}
