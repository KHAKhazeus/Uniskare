package com.uniskare.eureka_skill.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
@Table(name = "refund_pic", schema = "sedb", catalog = "")
@IdClass(RefundPicPK.class)
public class RefundPic {
    private int refundId;
    private int index;
    private String url;

    @Id
    @Column(name = "refund_id")
    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
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
        RefundPic refundPic = (RefundPic) o;
        return refundId == refundPic.refundId &&
                index == refundPic.index &&
                Objects.equals(url, refundPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refundId, index, url);
    }
}
