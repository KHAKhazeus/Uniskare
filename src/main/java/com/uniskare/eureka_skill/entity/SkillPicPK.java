package com.uniskare.eureka_skill.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
public class SkillPicPK implements Serializable {
    private int skillId;
    private int pindex;

    @Column(name = "skill_id")
    @Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Column(name = "pindex")
    @Id
    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillPicPK that = (SkillPicPK) o;
        return skillId == that.skillId &&
                pindex == that.pindex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, pindex);
    }
}
