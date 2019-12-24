package com.uniskare.eureka_skill.dto;

import com.uniskare.eureka_skill.entity.Moment;
import com.uniskare.eureka_skill.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
@Data
@AllArgsConstructor
public class MomentShow {
    int momentId;
    String userId;
    String userName;
    String avatar;
    Timestamp time;
    String content;
    boolean islike;//是否被本人点赞--
    boolean canSee;
//    String pic;
    List<String> pics;//改成数组
    int likeNum;

    public MomentShow(int momentId,
                      String userId, String userName, String avatar,
                      Timestamp time, String content, boolean canSee,
                      List<String> pics,
                      int likeNum) {
        this.momentId = momentId;
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.time = time;
        this.content = content;
        this.canSee = canSee;
        this.pics = pics;
        this.likeNum = likeNum;
    }

    public MomentShow(Moment moment, User user, List<String> pics, int likeNum)
    {
        this(
                moment.getMomentId(),
                user.getUniUuid(),user.getUniNickName(),user.getUniAvatarUrl(),
                moment.getTime(), moment.getContent(),moment.getCanSee() == 1,
                pics,
                likeNum
        );
    }
}
