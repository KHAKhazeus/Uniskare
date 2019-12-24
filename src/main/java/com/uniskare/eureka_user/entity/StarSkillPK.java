package com.uniskare.eureka_user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StarSkillPK implements Serializable {
    private int skillId;
    private String starId;

    @Column(name = "skill_id")
    @Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Column(name = "star_id")
    @Id
    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarSkillPK that = (StarSkillPK) o;
        return skillId == that.skillId &&
                Objects.equals(starId, that.starId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, starId);
    }
}
