package com.uniskare.eureka_skill.dto;

/**
 * @author : SCH001
 * @description :
 */
import com.uniskare.eureka_skill.entity.Conversation;
import com.uniskare.eureka_skill.entity.Message;
import com.uniskare.eureka_skill.entity.User;
import lombok.Data;

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
    Byte onTop;

    public MessageInfo(int conversationId,String friendId,String friendName,String friendHeadUrl,String content,
                       String timeStr,int unread, Byte onTop){
        this.conversationId = conversationId;
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendHeadUrl = friendHeadUrl;
        this.content = content;
        this.timeStr = timeStr;
        this.unread = unread;
        isTouchMove = false;
    }

    public MessageInfo(User friend, Message message, Conversation conversation)
    {
        this(conversation.getConversationId(),friend.getUniUuid(),friend.getUniNickName(),friend.getUniAvatarUrl(),
                message.getContent(),message.getDate(),conversation.getUnread(), conversation.getOnTop()
                );
    }
}
