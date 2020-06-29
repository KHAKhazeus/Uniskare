package com.uniskare.eureka_user.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.Response.Code;
import com.uniskare.eureka_user.controller.Response.ResponseMessage;
import com.uniskare.eureka_user.dto.CodeResponse;
import com.uniskare.eureka_user.repository.MessageRepo;
import com.uniskare.eureka_user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author : LK
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/user") //基路径
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private String wxAppid = "wxc9c276e49f2795b7";
    private String wxSecret = "cd8c771af0d123028d7d9801b15c10b7";

    @RequestMapping(value = "/getLoginCode",method = RequestMethod.POST)
    public BaseResponse verityLoginCode(@RequestParam("code") String code) throws IOException {
        CodeResponse codeResponse = null;
        BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                , Code.OK
                , Code.NO_ERROR_MESSAGE
                , "loc"
                , "/user/register"
                , false);
        if(!code.equals("")){
            String params = "appid=" + wxAppid +"&secret="+wxSecret+"&js_code="+code+"&grant_type=authorization_code";
            String url = "https://api.weixin.qq.com/sns/jscode2session?"+params;
            String response = restTemplate.getForObject(url,String.class);


            logger.info("response:" + response);
            codeResponse = mapper.readValue(response,CodeResponse.class);
            if(codeResponse.getSession_key() != null){
                userService.register(codeResponse.getOpenid());
            }
            baseResponse.setData(codeResponse);

        }

        return baseResponse;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponse login(@RequestBody JSONObject json){
        return userService.login(json);
    }

    @RequestMapping(value = "/information/{id}",method = RequestMethod.GET)
    public BaseResponse getUserInfo(@PathVariable("id") String id){
        return userService.getUserInfo(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public BaseResponse updateUserInfo(@RequestBody JSONObject jsonObject){
        return userService.updateUserInfo(jsonObject);
    }

    @RequestMapping(value = "/{id}/login/update",method = RequestMethod.POST)
    public BaseResponse loginWithUpdate(@PathVariable("id") String id,@RequestBody JSONObject jsonObject){
        return userService.loginWithUpdate(jsonObject,id);
    }







}
