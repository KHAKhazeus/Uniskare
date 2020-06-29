package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skill_pic", schema = "sedb", catalog = "")
@IdClass(SkillPicPK.class)
public class SkillPic {
    private int skillId;
    private int index;
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
    @Column(name = "index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
                index == skillPic.index &&
                Objects.equals(url, skillPic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, index, url);
    }
}
