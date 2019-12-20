package com.uniskare.eureka_skill.dto;

import com.uniskare.eureka_skill.entity.User;

/**
 * @author : Bhy
 * @description ：
 */
public interface SkillDTO {
    String getSkillId();
    String getContent();
    String getTitle();
    String getCover();
    //获得头像必须这样，没有办法直接调用Skill的getUserAvatar的接口
    UserAvatar getUser();
    interface UserAvatar {
        String getUniAvatarUrl();
    }

}
