package com.uniskare.eureka_user.entity;

import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "star_skill", schema = "sedb", catalog = "")
@javax.persistence.IdClass(StarSkillPK.class)
public class StarSkill {
    private int skillId;
    private String starId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "star_id")
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
        StarSkill starSkill = (StarSkill) o;
        return skillId == starSkill.skillId &&
                Objects.equals(starId, starSkill.starId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, starId);
    }
}
