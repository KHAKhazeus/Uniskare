package com.uniskare.eureka_skill.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
public class SkillPicPK implements Serializable {
    private int skillId;
    private int index;

    @javax.persistence.Column(name = "skill_id")
    @javax.persistence.Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @javax.persistence.Column(name = "index")
    @javax.persistence.Id
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
        SkillPicPK that = (SkillPicPK) o;
        return skillId == that.skillId &&
                index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, index);
    }
}
