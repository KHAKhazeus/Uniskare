package com.uniskare.eureka_skill.controller;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.entity.Message;
import org.springframework.web.bind.annotation.*;

/**
 * @author : SCH001
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/message") //基路径
public class MessageController {
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResponse insertMessage(@ModelAttribute Message message, String userId, String otherId)
    {
        //todo
        System.out.println(message);
        return null;
    }

    @RequestMapping(value = "/select/{userId}",method = RequestMethod.GET)
    public BaseResponse selectChatInfo(@PathVariable("userId") String id){
        return null;
    }
}
