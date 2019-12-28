package com.uniskare.eureka_skill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : SCH001
 * @description :
 */
@AllArgsConstructor
@Data
public class FollowInfo {
    private String userAvatar;
    private String userName;
    private String userMotto;
    private String userId;
    private boolean isFollow;
}
