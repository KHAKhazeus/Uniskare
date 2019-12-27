package com.uniskare.eureka_skill.entity;

import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
@javax.persistence.IdClass(RelationPK.class)
@AllArgsConstructor
public class Relation {
    private String followId;
    private String fanId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "follow_id")
    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "fan_id")
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
        Relation relation = (Relation) o;
        return Objects.equals(followId, relation.followId) &&
                Objects.equals(fanId, relation.fanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, fanId);
    }
}
