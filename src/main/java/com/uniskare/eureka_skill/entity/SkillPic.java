package com.uniskare.eureka_skill.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
@Table(name = "skill_pic", schema = "sedb", catalog = "")
@IdClass(SkillPicPK.class)
public class SkillPic {
    private int skillId;
    private int pindex;
    private String url;

    @Id
    @Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Id
    @Column(name = "pindex")
    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
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
        SkillPic skillPic = (SkillPic) o;
        return skillId == skillPic.skillId &&
                pindex == skillPic.pindex &&
                Objects.equals(url, skillPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, pindex, url);
    }
}
