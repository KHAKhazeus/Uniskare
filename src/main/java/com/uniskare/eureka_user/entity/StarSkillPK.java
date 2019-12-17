package com.uniskare.eureka_user.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
public class StarSkillPK implements Serializable {
    private int skillId;
    private String starId;

    @javax.persistence.Column(name = "skill_id")
    @javax.persistence.Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @javax.persistence.Column(name = "star_id")
    @javax.persistence.Id
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
