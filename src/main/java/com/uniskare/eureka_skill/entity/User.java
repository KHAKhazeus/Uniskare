package com.uniskare.eureka_skill.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
public class User {
    private String uniUuid;
    private String uniAvatarUrl;
    private String uniNickName;
    private Byte uniSex;
    private String uniIndiSign;
    private Byte uniIsStu;
    private Byte uniIsPass;
    private Byte uniPassPhone;
    private String uniPhoneNum;
    private Byte changeNickName;
    private Byte changeAvatar;

    @Id
    @Column(name = "uni_uuid")
    public String getUniUuid() {
        return uniUuid;
    }

    public void setUniUuid(String uniUuid) {
        this.uniUuid = uniUuid;
    }

    @Basic
    @Column(name = "uni_avatar_url")
    public String getUniAvatarUrl() {
        return uniAvatarUrl;
    }

    public void setUniAvatarUrl(String uniAvatarUrl) {
        this.uniAvatarUrl = uniAvatarUrl;
    }

    @Basic
    @Column(name = "uni_nick_name")
    public String getUniNickName() {
        return uniNickName;
    }

    public void setUniNickName(String uniNickName) {
        this.uniNickName = uniNickName;
    }

    @Basic
    @Column(name = "uni_sex")
    public Byte getUniSex() {
        return uniSex;
    }

    public void setUniSex(Byte uniSex) {
        this.uniSex = uniSex;
    }

    @Basic
    @Column(name = "uni_indi_sign")
    public String getUniIndiSign() {
        return uniIndiSign;
    }

    public void setUniIndiSign(String uniIndiSign) {
        this.uniIndiSign = uniIndiSign;
    }

    @Basic
    @Column(name = "uni_is_stu")
    public Byte getUniIsStu() {
        return uniIsStu;
    }

    public void setUniIsStu(Byte uniIsStu) {
        this.uniIsStu = uniIsStu;
    }

    @Basic
    @Column(name = "uni_is_pass")
    public Byte getUniIsPass() {
        return uniIsPass;
    }

    public void setUniIsPass(Byte uniIsPass) {
        this.uniIsPass = uniIsPass;
    }

    @Basic
    @Column(name = "uni_pass_phone")
    public Byte getUniPassPhone() {
        return uniPassPhone;
    }

    public void setUniPassPhone(Byte uniPassPhone) {
        this.uniPassPhone = uniPassPhone;
    }

    @Basic
    @Column(name = "uni_phone_num")
    public String getUniPhoneNum() {
        return uniPhoneNum;
    }

    public void setUniPhoneNum(String uniPhoneNum) {
        this.uniPhoneNum = uniPhoneNum;
    }

    @Basic
    @Column(name = "change_nick_name")
    public Byte getChangeNickName() {
        return changeNickName;
    }

    public void setChangeNickName(Byte changeNickName) {
        this.changeNickName = changeNickName;
    }

    @Basic
    @Column(name = "change_avatar")
    public Byte getChangeAvatar() {
        return changeAvatar;
    }

    public void setChangeAvatar(Byte changeAvatar) {
        this.changeAvatar = changeAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uniUuid, user.uniUuid) &&
                Objects.equals(uniAvatarUrl, user.uniAvatarUrl) &&
                Objects.equals(uniNickName, user.uniNickName) &&
                Objects.equals(uniSex, user.uniSex) &&
                Objects.equals(uniIndiSign, user.uniIndiSign) &&
                Objects.equals(uniIsStu, user.uniIsStu) &&
                Objects.equals(uniIsPass, user.uniIsPass) &&
                Objects.equals(uniPassPhone, user.uniPassPhone) &&
                Objects.equals(uniPhoneNum, user.uniPhoneNum) &&
                Objects.equals(changeNickName, user.changeNickName) &&
                Objects.equals(changeAvatar, user.changeAvatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniUuid, uniAvatarUrl, uniNickName, uniSex, uniIndiSign, uniIsStu, uniIsPass, uniPassPhone, uniPhoneNum, changeNickName, changeAvatar);
    }
}
