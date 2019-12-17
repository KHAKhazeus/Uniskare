package com.uniskare.eureka_user.entity;

import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "skill_pic", schema = "sedb", catalog = "")
@javax.persistence.IdClass(SkillPicPK.class)
public class SkillPic {
    private int skillId;
    private int index;
    private String url;

    @javax.persistence.Id
    @javax.persistence.Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "url")
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
                index == skillPic.index &&
                Objects.equals(url, skillPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, index, url);
    }
}
