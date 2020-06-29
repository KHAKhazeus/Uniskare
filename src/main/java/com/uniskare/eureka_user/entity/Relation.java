package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(RelationPK.class)
public class Relation {
    private String followId;
    private String fanId;

    @Id
    @Column(name = "follow_id")
    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    @Id
    @Column(name = "fan_id")
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
