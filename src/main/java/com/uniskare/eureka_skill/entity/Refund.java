package com.uniskare.eureka_skill.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
public class Refund {
    private int refundId;
    private int orderId;
    private Timestamp time;
    private String reason;
    private Byte status;

    @Id
    @Column(name = "refund_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    @Basic
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Refund refund = (Refund) o;
        return refundId == refund.refundId &&
                orderId == refund.orderId &&
                Objects.equals(time, refund.time) &&
                Objects.equals(reason, refund.reason) &&
                Objects.equals(status, refund.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refundId, orderId, time, reason, status);
    }
}
