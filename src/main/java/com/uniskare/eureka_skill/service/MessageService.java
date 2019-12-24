package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.dto.MessageInfo;
import com.uniskare.eureka_skill.entity.Message;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
public interface MessageService {
    public int insertMessageInfo(JSONObject body);

    public List<MessageInfo> getChatInfo(String id);
}
