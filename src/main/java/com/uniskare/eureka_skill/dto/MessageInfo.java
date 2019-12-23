package com.uniskare.eureka_skill.dto;

/**
 * @author : SCH001
 * @description :
 */
import lombok.Data;

//摘自jue chen 大佬
@Data
public class MessageInfo {
    int conversationId;
    String friendId;
    String friendName;
    String friendHeadUrl;
    String content;
    String timeStr;
    int unread;
    boolean isTouchMove;

    public MessageInfo(int conversationId,String friendId,String friendName,String friendHeadUrl,String content,
                       String timeStr,int unread){
        this.conversationId = conversationId;
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendHeadUrl = friendHeadUrl;
        this.content = content;
        this.timeStr = timeStr;
        this.unread = unread;
        isTouchMove = false;
    }
}
