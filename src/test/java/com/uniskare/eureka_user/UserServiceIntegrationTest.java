package com.uniskare.eureka_user;


import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.dto.UserInfo;
import com.uniskare.eureka_user.entity.Moment;
import com.uniskare.eureka_user.entity.Relation;
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.repository.MomentRepo;
import com.uniskare.eureka_user.repository.RelationRepo;
import com.uniskare.eureka_user.repository.UserRepo;
import com.uniskare.eureka_user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static com.uniskare.eureka_user.service.Helper.Const.*;
import static com.uniskare.eureka_user.service.Helper.Const.AVATAR;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_user")
    static class InnerConfig{}

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RelationRepo relationRepo;

    @Autowired
    private MomentRepo momentRepo;

    @Test
    public void IT_TC_001_001_001(){
        String id = "";

        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_001_001_002(){

        String id = "7862378";

        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(true);

    }

    @Test
    public void IT_TC_001_001_003(){

        String id = "oEHOK5WdGlU4fD9K_1YrZDT9pBIA";
        User user = new User();
        user.setUniUuid(id);

        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(true);
    }

    @Test
    public void IT_TC_001_002_001_001(){
        String userId= "";
        String nickname = "dwdw";
        String avatarUrl = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_001_002_001_002(){
        String userId= "test153";
        String nickname = "";
        String avatarUrl = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("昵称不能为空");
    }

    @Test
    public void IT_TC_001_002_001_003(){
        String userId= "test153";
        String nickname = "test1";
        String avatarUrl = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("头像不能为空");
    }

    @Test
    public void IT_TC_001_002_002(){
        String userId= "765432";
        String nickname = "dwdw";
        String avatarUrl = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }
    @Test
    public void IT_TC_001_002_003(){
        String userId= "oEHOK5WdGlU4fD9K_1YrZDT9pBIA";
        String nickname = "dwdw";
        String avatarUrl = "dwdw";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(true);
        User user1 = userRepo.findByUniUuid(userId);
        Assertions.assertThat(user1.getUniNickName()).endsWith(nickname);
        Assertions.assertThat(user1.getUniAvatarUrl()).endsWith(avatarUrl);
    }

    @Test
    public void IT_TC_001_002_004(){
        String userId= "test153";
        String nickname = "dwdw";
        String avatarUrl = "dwdw";
        User user = new User();
        user.setUniNickName(nickname);
        user.setUniAvatarUrl(avatarUrl);
        user.setChangeNickName((byte)1);
        user.setChangeAvatar((byte)1);
        user.setUniUuid(userId);
        userRepo.save(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(true);
        User user1 = userRepo.findByUniUuid(userId);
        Assertions.assertThat(user1.getUniAvatarUrl()).isEqualTo(avatarUrl);
        Assertions.assertThat(user1.getUniNickName()).isEqualTo(nickname);

    }


    @Test
    public void IT_TC_001_003_001(){
        String userId= "";
        BaseResponse result = userService.getUserInfo(userId);
        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_001_003_002(){
        String userId= "765432";
        BaseResponse result = userService.getUserInfo(userId);
        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void IT_TC_001_003_003(){
        String userId= "test153";
        User user = new User();
        user.setUniUuid(userId);

        UserInfo userInfo = new UserInfo(user.getUniUuid(),user.getUniAvatarUrl(),
                user.getUniNickName(),user.getUniSex(),user.getUniIndiSign(),user.getUniIsStu(),
                user.getUniSchool(),user.getUniPhoneNum(),user.getChangeNickName(),user.getChangeAvatar(),
                user.getUniPassPhone(),0,0,0);

        userRepo.save(user);




        BaseResponse result = userService.getUserInfo(userId);
        Assertions.assertThat(result.getData()).isEqualTo(userInfo);
    }


    @Test
    public void IT_TC_001_003_004(){
        String userId= "test153";
        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        UserInfo userInfo = new UserInfo(user.getUniUuid(),user.getUniAvatarUrl(),
                user.getUniNickName(),user.getUniSex(),user.getUniIndiSign(),user.getUniIsStu(),
                user.getUniSchool(),user.getUniPhoneNum(),user.getChangeNickName(),user.getChangeAvatar(),
                user.getUniPassPhone(),2,2,1);

        Moment moment = new Moment();
        moment.setUserId(userId);
        momentRepo.save(moment);
        Moment moment1 = new Moment();
        moment1.setContent("mm");
        moment1.setUserId(userId);


        momentRepo.save(moment1);

        List<Moment> moments = momentRepo.findByUserId(userId);
        String userIdOne = "11";
        String userIdTwo = "22";
        User user1 =new User();
        user1.setUniUuid(userIdOne);
        User user2 = new User();
        user2.setUniUuid(userIdTwo);

//        List<Relation> relations = new ArrayList<>();
        Relation relation = new Relation();
        relation.setFanId(userId );
        relation.setFollowId(user1.getUniUuid());
        relationRepo.save(relation);

        relation.setFollowId(user2.getUniUuid());
        relationRepo.save(relation);

//        List<Relation> fans = new ArrayList<>();
        relation.setFanId(user2.getUniUuid());
        relation.setFollowId(userId);
        relationRepo.save(relation);






        BaseResponse result = userService.getUserInfo(userId);
        Assertions.assertThat(result.getData()).isEqualTo(userInfo);
    }





    @Test
    public void IT_TC_001_004_001_001(){
        String userId = "";
        String nickName = "nicknick";
        String avatar = "awdw";
        String phone = "18754128788";
        String indiSign = "ww";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_001_004_001_002(){
        String userId = "test153";
        String nickName = "";
        String avatar = "awdw";
        String phone = "18754128788";
        String indiSign = "ww";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("昵称不能为空");
    }

    @Test
    public void IT_TC_001_004_001_003(){
        String userId = "test153";
        String nickName = "nicknick";
        String avatar = "";
        String phone = "18754128788";
        String indiSign = "ww";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("头像不能为空");
    }

    @Test
    public void IT_TC_001_004_001_004(){
        String userId = "test153";
        String nickName = "nicknick";
        String avatar = "awdw";
        String phone = "";
        String indiSign = "ww";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("手机号不能为空");
    }

    @Test
    public void IT_TC_001_004_002(){
        String userId = "7664242";
        String nickName = "hello";
        String avatar = "dwdwdwd";
        String phone = "18754128788";
        String indiSign = "";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void IT_TC_001_004_003(){
        String userId = "test153";
        String nickName = "dwdw";
        String avatar = "dwdw";
        String phone = "18372";
        String indiSign = "fefefef";
        User user = new User();
        user.setUniUuid(userId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        userRepo.save(user);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("手机号不符合要求");
    }

    @Test
    public void IT_TC_001_004_004(){
        String userId = "test153";
        String nickName = "dwdw";
        String avatar = "dwdw";
        String phone = "19287372992";
        String indiSign = "fefefef";

        User user = new User();
        user.setUniUuid(userId);
        user.setUniNickName("aa");
        user.setUniAvatarUrl("BB");
        user.setUniPhoneNum("3213123");
        user.setUniIndiSign("");
        user.setChangeAvatar((byte)0);
        user.setChangeNickName((byte)0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        userRepo.save(user);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(true);

        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(check.getUniNickName()).isEqualTo(nickName);
        Assertions.assertThat(check.getUniAvatarUrl()).isEqualTo(avatar);
        Assertions.assertThat(check.getUniPhoneNum()).isEqualTo(phone);
        Assertions.assertThat(check.getUniIndiSign()).isEqualTo(indiSign);
        Assertions.assertThat(check.getChangeAvatar()).isEqualTo((byte)1);
        Assertions.assertThat(check.getChangeNickName()).isEqualTo((byte)1);
    }


    public void IT_TC_001_004_005(){
        String userId = "test153";
        String nickName = "dwdw";
        String avatar = "dwdw";
        String phone = "19287372992";
        String indiSign = "fefefef";

        User user = new User();
        user.setUniUuid(userId);
        user.setUniNickName("aa");
        user.setUniAvatarUrl("BB");
        user.setUniPhoneNum("3213123");
        user.setUniIndiSign("");
        user.setChangeAvatar((byte)1);
        user.setChangeNickName((byte)1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        userRepo.save(user);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(true);

        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(check.getUniNickName()).isEqualTo(nickName);
        Assertions.assertThat(check.getUniAvatarUrl()).isEqualTo(avatar);
        Assertions.assertThat(check.getUniPhoneNum()).isEqualTo(phone);
        Assertions.assertThat(check.getUniIndiSign()).isEqualTo(indiSign);
        Assertions.assertThat(check.getChangeAvatar()).isEqualTo((byte)1);
        Assertions.assertThat(check.getChangeNickName()).isEqualTo((byte)1);
    }



    @Test
    public void IT_TC_001_005_001_001(){
        String userId = "";
        String nickName = "kjwd";
        String avatar = "qdqwd";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        BaseResponse result = userService.loginWithUpdate(jsonObject,userId);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_001_005_001_002(){
        String userId = "test153";
        String nickName = "";
        String avatar = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        BaseResponse result = userService.loginWithUpdate(jsonObject,userId);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("昵称不能为空");
    }

    @Test
    public void IT_TC_001_005_001_003(){
        String userId = "test153";
        String nickName = "kjwd";
        String avatar = "";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        BaseResponse result = userService.loginWithUpdate(jsonObject,userId);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("头像不能为空");
    }



    @Test
    public void IT_TC_001_005_002(){
        String userId = "765432";
        String nickName = "kjwd";
        String avatar = "wdwdwwd";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        BaseResponse result = userService.loginWithUpdate(jsonObject,userId);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }
    @Test
    public void IT_TC_001_005_003(){
        String userId = "test153";
        String nickName = "dwdw";
        String avatar = "dwdw";


        User user = new User();
        user.setUniUuid(userId);
        user.setUniNickName("aaa");
        user.setUniAvatarUrl("bbb");
        user.setChangeAvatar((byte) 0);
        user.setChangeNickName((byte) 0);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        userRepo.save(user);

        BaseResponse result = userService.loginWithUpdate(jsonObject,userId);
        Assertions.assertThat(result.getData()).isEqualTo(true);

        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(check.getUniNickName()).isEqualTo(nickName);
        Assertions.assertThat(check.getUniAvatarUrl()).isEqualTo(avatar);
        Assertions.assertThat(check.getChangeAvatar()).isEqualTo((byte)0);
        Assertions.assertThat(check.getChangeNickName()).isEqualTo((byte)0);

    }

    @Test
    public void IT_TC_001_005_004(){
        String userId = "test153";
        String nickName = "dwdw";
        String avatar = "dwdw";

        User user = new User();
        user.setUniUuid(userId);
        user.setUniNickName("aaa");
        user.setUniAvatarUrl("bbb");
        user.setChangeAvatar((byte) 1);
        user.setChangeNickName((byte) 1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        userRepo.save(user);

        BaseResponse result = userService.loginWithUpdate(jsonObject,userId);
        Assertions.assertThat(result.getData()).isEqualTo(true);

        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(check.getUniNickName()).isEqualTo("aaa");
        Assertions.assertThat(check.getUniAvatarUrl()).isEqualTo("bbb");
        Assertions.assertThat(check.getChangeAvatar()).isEqualTo((byte)1);
        Assertions.assertThat(check.getChangeNickName()).isEqualTo((byte)1);
    }


    @Test
    public void IT_TC_001_006_001_001(){
        String userId = "";
        int status = 0;
        String school = "2e2e";

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void IT_TC_001_006_001_002(){
        String userId = "2323";
        int status = 0;
        String school = "";

        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }


    @Test
    public void IT_TC_001_006_002(){
        String userId = "765432";
        int status = 1;
        String school = "kdjwk";

        User user = new User();
        user.setUniUuid(userId);



        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @Test
    public void IT_TC_001_006_003_001(){
        String userId = "test153";
        int status = -1;
        String school = "dwdw";

        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void IT_TC_001_006_003_002(){
        String userId = "test153";
        int status = 5;
        String school = "dwdw";

        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }


    @Test
    public void IT_TC_001_006_004(){
        String userId = "test153";
        int status = 2;
        String school = "dwdw";
        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(true);

        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(check.getUniIsStu()).isEqualTo(status);
    }

    @Test
    public void IT_TC_001_006_005(){
        String userId = "test153";
        int status = 0;
        String school = "dwdw";
        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(true);

        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(check.getUniIsStu()).isEqualTo(status);
    }

    @Test
    public void IT_TC_001_006_006(){
        String userId = "test153";
        int status = 1;
        String school = "dwdw";
        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(true);

        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(check.getUniIsStu()).isEqualTo(status);
    }

}
