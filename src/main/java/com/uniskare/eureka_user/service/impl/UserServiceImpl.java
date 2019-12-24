package com.uniskare.eureka_user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.Response.Code;
import com.uniskare.eureka_user.controller.Response.ResponseMessage;
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.repository.UserRepo;
import com.uniskare.eureka_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

import static com.uniskare.eureka_user.service.Helper.Const.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void register(String open_id) {
        User user = userRepo.findByUniUuid(open_id);
        if(user == null){
            user = new User();
            user.setUniUuid(open_id);
            userRepo.save(user);
        }
    }

    @Override
    public BaseResponse login(JSONObject json) {
        try{
            String user_id = json.getString(USER_ID);
            String nick_name = json.getString(NICK_NAME);
            String avatar = json.getString(AVATAR);
            User user = userRepo.findByUniUuid(user_id);
            if(user.getChangeNickName() == null || user.getChangeNickName()==0){
                user.setUniNickName(nick_name);
            }
            if(user.getChangeNickName() == null || user.getChangeAvatar()==0){
                user.setUniAvatarUrl(avatar);
            }
            user.setChangeNickName((byte) 0);
            user.setChangeAvatar((byte) 0);
            user.setUniIndiSign("这个人很懒,什么都不想说");
            userRepo.save(user);
            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.LOGIN_SUCCESS,
                    "/user/login",
                    null);
        }catch (Exception e){
            System.out.println(e.toString());
            return new BaseResponse(
                    null, Code.BAD_REQUEST, e.toString(),null, null, null
            );
        }

    }

    @Override
    public BaseResponse getUserInfo(String id) {
        try{
            User user = userRepo.findByUniUuid(id);
            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.QUERY_SUCCESS,
                    "/user/getUserInfo",
                    user);
        }catch (Exception e){
            System.out.println(e.toString());
            return new BaseResponse(
                    null, Code.BAD_REQUEST, e.toString(),null, null, null
            );
        }
    }

    @Override
    public BaseResponse updateUserInfo(JSONObject json) {
        try{
            String userId = json.getString(USER_ID);
            String avatar = json.getString(AVATAR);
            String phone = json.getString(PHONE);
            String indiSign = json.getString(INDI_SIGN);
            String nickName = json.getString(NICK_NAME);

            User user = userRepo.findByUniUuid(userId);
            if(avatar != null && (user.getChangeAvatar()==0) && !(user.getUniAvatarUrl().equals(avatar))){
                user.setChangeAvatar((byte)1);
            }
            if(nickName!=null && (user.getChangeNickName()==0) && !(user.getUniNickName().equals(nickName))){
                user.setChangeNickName((byte)1);
            }
            user.setUniNickName(nickName);
            user.setUniIndiSign(indiSign);
            user.setUniPhoneNum(phone);
            user.setUniAvatarUrl(avatar);
            userRepo.save(user);

            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.QUERY_SUCCESS,
                    "/user/getUserInfo",
                    null);
        }catch (Exception e){
            System.out.println(e.toString());
            return new BaseResponse(
                    null, Code.BAD_REQUEST, e.toString(),null, null, null
            );
        }
    }

    @Override
    public BaseResponse loginWithUpdate(JSONObject jsonObject,String id) {
        try{
            User user = userRepo.findByUniUuid(id);
            String avatar = jsonObject.getString(AVATAR);
            String nickName =jsonObject.getString(NICK_NAME);
            if(user.getChangeNickName() == null || user.getChangeNickName()==0){
                user.setUniNickName(nickName);
            }
            if(user.getChangeNickName() == null || user.getChangeAvatar()==0){
                user.setUniAvatarUrl(avatar);
            }
            userRepo.save(user);
            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.QUERY_SUCCESS,
                    "/user/loginWithUpdate",
                    null);
        }catch (Exception e){
            System.out.println(e.toString());
            return new BaseResponse(
                    null, Code.BAD_REQUEST, e.toString(),null, null, null
            );
        }
    }

    @Override
    public Boolean upLoadStatus(String id, int status, String school) {
        try{
            User user = userRepo.findByUniUuid(id);
            user.setUniIsStu(status);
            if(school != null){
                user.setUniSchool(school);
            }
            userRepo.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}
