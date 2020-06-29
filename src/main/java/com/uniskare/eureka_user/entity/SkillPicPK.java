package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SkillPicPK implements Serializable {
    private int skillId;
    private int index;

    @Column(name = "skill_id")
    @Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Column(name = "index")
    @Id
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
