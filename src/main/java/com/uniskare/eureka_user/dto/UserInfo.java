package com.uniskare.eureka_user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String uniUuid;
    private String uniAvatarUrl;
    private String uniNickName;
    private Byte uniSex;
    private String uniIndiSign;
    private Integer uniIsStu;
    private String uniSchool;
    private String uniPhoneNum;
    private Byte changeNickName;
    private Byte changeAvatar;
    private Byte uniPassPhone;
    int momentNum;
    int followers;
    int fans;
}
