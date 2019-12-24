package com.uniskare.eureka_user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RelationPK implements Serializable {
    private String followId;
    private String fanId;

    @Column(name = "follow_id")
    @Id
    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    @Column(name = "fan_id")
    @Id
    public String getFanId() {
        return fanId;
    }

    public void setFanId(String fanId) {
        this.fanId = fanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationPK that = (RelationPK) o;
        return Objects.equals(followId, that.followId) &&
                Objects.equals(fanId, that.fanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, fanId);
    }
}
