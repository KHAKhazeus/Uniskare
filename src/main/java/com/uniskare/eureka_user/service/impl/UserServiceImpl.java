package com.uniskare.eureka_user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.Response.Code;
import com.uniskare.eureka_user.controller.Response.ResponseMessage;
import com.uniskare.eureka_user.dto.UserInfo;
import com.uniskare.eureka_user.entity.*;
import com.uniskare.eureka_user.repository.*;
import com.uniskare.eureka_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.uniskare.eureka_user.service.Helper.Const.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MomentRepo momentRepo;
    @Autowired
    private RelationRepo relationRepo;
    @Autowired
    private ConversationRepo conversationRepo;
    @Autowired
    private MessageRepo messageRepo;

    @Override
    public BaseResponse register(String open_id) {
        Boolean judge = false;
        String returnMessage = ResponseMessage.LOGIN_SUCCESS;

        if(!open_id.equals("")){
            User user = userRepo.findByUniUuid(open_id);

                user = new User();
                user.setUniUuid(open_id);
                user.setUniSchool("同济大学");
                user.setUniIsStu(2); // no certification
                userRepo.save(user);
                judge = true;

                Conversation conversation = conversationRepo.findByUserIdAndOtherId(open_id,"kefu");
                if(conversation == null){
                    conversation = conversationRepo.findByUserIdAndOtherId("kefu",open_id);
                }
                if(conversation == null){
                    conversation = new Conversation();
                    conversation.setUserId(open_id);
                    conversation.setOtherId("kefu");
                    conversation.setOnTop((byte) 1);
                    conversation.setUnread(1);
                    conversationRepo.save(conversation);
                }


                Message message = new Message();
                message.setConversationId(conversation.getConversationId());
                message.setContent("欢迎来到优技享!");
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
                Date date = new Date();
                message.setDate(sdf.format(date));
                messageRepo.save(message);
        }else{
            returnMessage = "不合法的id";
        }





        return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                returnMessage,
                "/user/register",
                judge);
    }

    @Override
    public BaseResponse login(JSONObject json) {
        try{
            String user_id = json.getString(USER_ID);
            String nick_name = json.getString(NICK_NAME);
            String avatar = json.getString(AVATAR);
            String returnMessage = "";
            boolean judge = false;
            if(user_id.equals("")){
                returnMessage = "不合法的id";
            }
            else if(nick_name.equals("")){
                returnMessage = "昵称不能为空";
            }
            else if(avatar.equals("")){
                returnMessage = "头像不能为空";
            }else{
                User user = userRepo.findByUniUuid(user_id);
                if(user == null){
                    returnMessage = "id不存在";
                }else{
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
                    judge = true;
                }

            }


            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    returnMessage,
                    "/user/login",
                    judge);
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

            String returnMessage = "";
            UserInfo userInfo = null;
            if(id.equals("")){
                returnMessage = "不合法的id";
            }else{
                User user = userRepo.findByUniUuid(id);
                if(user == null){
                    returnMessage = "id不存在";
                }else{
                    List<Moment> moments = momentRepo.findByUserId(id);
                    List<Relation> fans = relationRepo.findAllByFollowId(id);
                    List<Relation> follows = relationRepo.findAllByFanId(id);
                    int momNum = 0;
                    int fansNum = 0;
                    int followersNum = 0;
                    if(moments != null){
                        momNum = moments.size();
                    }
                    if(fans != null){
                        fansNum = fans.size();
                    }
                    if(follows != null) {
                        followersNum = follows.size();
                    }
                    userInfo = new UserInfo(user.getUniUuid(),user.getUniAvatarUrl(),
                            user.getUniNickName(),user.getUniSex(),user.getUniIndiSign(),user.getUniIsStu(),
                            user.getUniSchool(),user.getUniPhoneNum(),user.getChangeNickName(),user.getChangeAvatar(),
                            user.getUniPassPhone(),momNum,followersNum,fansNum);

                }
            }



            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    returnMessage,
                    "/user/getUserInfo",
                    userInfo);
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
            boolean judge = false;
            String returnMessage = ResponseMessage.QUERY_SUCCESS;
            String userId = json.getString(USER_ID);
            String avatar = json.getString(AVATAR);
            String phone = json.getString(PHONE);
            String indiSign = json.getString(INDI_SIGN);
            String nickName = json.getString(NICK_NAME);
            if(userId.equals("")){
                returnMessage = "不合法的id";
            }else if(nickName.equals("")){
                returnMessage = "昵称不能为空";
            }else if(avatar.equals("")){
                returnMessage = "头像不能为空";
            }else if(phone.equals("")){
                returnMessage = "手机号不能为空";
            }else{
                User user = userRepo.findByUniUuid(userId);
                if(user == null){
                    returnMessage = "id不存在";
                }else if(phone.length()!=11){
                    returnMessage = "手机号不符合要求";
                }else{
                    if(avatar != null && (user.getChangeAvatar()==null ||user.getChangeAvatar()==0) && !(user.getUniAvatarUrl().equals(avatar))){
                        user.setChangeAvatar((byte)1);
                    }
                    if(nickName!=null && (user.getChangeNickName()==null || user.getChangeNickName()==0) && !(user.getUniNickName().equals(nickName))){
                        user.setChangeNickName((byte)1);
                    }
                    user.setUniNickName(nickName);
                    user.setUniIndiSign(indiSign);
                    user.setUniPhoneNum(phone);
                    user.setUniAvatarUrl(avatar);
                    userRepo.save(user);
                    judge = true;
                }


            }



            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    returnMessage,
                    "/user/getUserInfo",
                    judge);
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
            String returnMessage = ResponseMessage.QUERY_SUCCESS;
            boolean judge = false;
            String avatar = jsonObject.getString(AVATAR);
            String nickName =jsonObject.getString(NICK_NAME);
            if(id.equals("")){
                returnMessage = "不合法的id";
            }else if(nickName.equals("")){
                returnMessage = "昵称不能为空";
            }else if(avatar.equals("")){
                returnMessage = "头像不能为空";
            }else{
                User user = userRepo.findByUniUuid(id);
                if(user == null){
                    returnMessage = "id不存在";
                }else{
                    if(user.getChangeNickName() == null || user.getChangeNickName()==0){
                        user.setUniNickName(nickName);
                    }
                    if(user.getChangeNickName() == null || user.getChangeAvatar()==0){
                        user.setUniAvatarUrl(avatar);
                    }
                    userRepo.save(user);
                    judge = true;
                }

            }

            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    returnMessage,
                    "/user/loginWithUpdate",
                    judge);
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
            if(school.equals("")){
                throw new Exception();
            }
            if(status < 0 || status > 2){
                throw new Exception();
            }
            user.setUniSchool(school);

            userRepo.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}
