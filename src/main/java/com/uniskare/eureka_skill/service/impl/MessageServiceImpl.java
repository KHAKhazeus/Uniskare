package com.uniskare.eureka_skill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.dto.MessageInfo;
import com.uniskare.eureka_skill.entity.Conversation;
import com.uniskare.eureka_skill.entity.Message;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.repository.ConversationRepo;
import com.uniskare.eureka_skill.repository.MessageRepo;
import com.uniskare.eureka_skill.repository.UserRepo;
import com.uniskare.eureka_skill.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.uniskare.eureka_skill.service.Helper.Const.*;

/**
 * @author : SCH001
 * @description :
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    ConversationRepo conversationRepo;
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    UserRepo userRepo;

    public int insertMessageInfo(JSONObject body){
        String userId = body.getString(USER_ID);
        String otherId = body.getString(OTHER_ID);

        System.out.println(userId + " "+otherId);
        Conversation conversation = conversationRepo.findByUserIdAndOtherId(userId, otherId);
        if(conversation == null)
        {
            //新建对话
            conversation = new Conversation(userId,otherId);
            conversationRepo.save(conversation);
        }

        String date = body.getString(DATE);
        String content = body.getString(CONTENT);

        int con_id = conversation.getConversationId();
        Message message = new Message(con_id,content,date);

        messageRepo.save(message);
        conversation.incUnread(1);
        conversationRepo.save(conversation);

        return con_id;
    }

    @Override
    public List<MessageInfo> getChatInfo(String id) {
        //会话可能是自己或者其他人发起的
        List<Conversation> conversations = conversationRepo.findByUserIdOrOtherId(id, id);

        List<MessageInfo> ret = new ArrayList<>();

        for(Conversation conversation:conversations)
        {
            int con_id = conversation.getConversationId();
            String other_id = conversation.getOtherId();

            Message message = messageRepo.findByConversationId(con_id);
            User friend = userRepo.findByUniUuid(other_id);
            //Message Conversation Other
            MessageInfo messageInfo = new MessageInfo(friend,message,conversation);

            ret.add(messageInfo);
        }

        //先根据置顶
        //后根据时间排序
        ret.sort(
                (x,y) ->
                {
                    int top_ = y.getOnTop() - x.getOnTop();
                    if(top_ == 0)
                    {
                        return y.getTimeStr().compareTo(x.getTimeStr());
                    }else{
                        return top_;
                    }
                }
        );

        return ret;
    }

}
