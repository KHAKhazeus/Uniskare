package com.uniskare.eureka_skill.mapper;


import com.uniskare.eureka_skill.dto.MessageInfo;
import com.uniskare.eureka_skill.entity.Conversation;
import com.uniskare.eureka_skill.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */

//摘自jue chen
public interface MessageMapper {
    @Insert({"insert into message value(#{conversationId}," +
            "#{content},#{date}) " +
            "on duplicate key update " +
            "content=#{content},date=#{date};" })
    @Options(useGeneratedKeys = true,keyProperty = "conversationId",keyColumn = "conversationId")
    int insertUser(Message message);

    @Select({"select conversationId from conversation where (userId = #{id1} and otherId = #{id2}) " +
            "or (userId=#{id2} and otherId=#{id1})"})
    int selectConversationId(@Param("id1") String id1,@Param("id2") String id2);


    @Insert({"insert into conversation value(#{conversationId}," +
            "#{userId},#{otherId})"})
    int insertConversation(Conversation conversation);

    @Select({"select count(conversationId) from conversation where (userId = #{id1} and otherId = #{id2}) "+
            " or (userId=#{id2} and otherId=#{id1})"})
    int checkExistOnConversation(@Param("id1") String id1,@Param("id2") String id2);

//    @Insert({"insert into unread value(#{conversationId},#{userId},#{unread}) " +
//            "on duplicate key update " +
//            "unread=#{unread}"})
//    int insertUnread(unread unread);

//    @Update({"Update unread SET unread=0 where conversationId=#{id} and userId=#{userId}"})
//    int updateUnread(@Param("id") int id,@Param("userId") String userId);


    @Select({"Select conversationId,uni_uuid as friendId,uni_nickName as friendName, " +
            "uni_avatarUrl as friendHeadUrl,content,date as timeStr,unread " +
            "from (select a.conversationId, a.userId, content, date, unread from " +
            "(select conversationId, otherId as userId from conversation where userId=#{userId} " +
            "union select conversationId, userId from conversation where otherId=#{userId}) " +
            "as a inner join message as b on a.conversationId = b.conversationId " +
            "inner join unread as c on a.conversationId = c.conversationId and c.userId=#{userId}) " +
            "as f inner join user on f.userId = user.uni_uuid"})
    List<MessageInfo> selectMessage(@Param("userId") String userId);
}
