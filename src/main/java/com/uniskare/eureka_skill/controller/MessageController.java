package com.uniskare.eureka_skill.controller;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.dto.MessageInfo;
import com.uniskare.eureka_skill.entity.Message;
import com.uniskare.eureka_skill.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/comm") //基路径
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/message/insert", method = RequestMethod.POST)
    public BaseResponse insertMessage(@RequestBody JSONObject body)
    {
        //todo
        int id = messageService.insertMessageInfo(body);
        return new BaseResponse(Code.OK,null,null,id);
    }

    @RequestMapping(value = "/message/select/{user_id}",method = RequestMethod.GET)
    public BaseResponse selectChatInfo(@PathVariable("user_id") String id){
        System.out.println(id);
        List<MessageInfo> messageInfos = messageService.getChatInfo(id);
        return new BaseResponse(messageInfos);
    }
}
