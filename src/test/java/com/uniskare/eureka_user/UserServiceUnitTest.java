package com.uniskare.eureka_user;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.inject.internal.cglib.proxy.$Factory;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.dto.UserInfo;
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.repository.*;
import com.uniskare.eureka_user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.uniskare.eureka_user.service.Helper.Const.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceUnitTest {

    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_user")
    static class InnerConfig{}

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private MomentRepo momentRepo;
    @MockBean
    private RelationRepo relationRepo;
    @MockBean
    private ConversationRepo conversationRepo;
    @MockBean
    private MessageRepo messageRepo;

    @Test
    public void UT_TC_001_001_001(){
        printTestCaseCode("UT_TC_001_001_001");
        String id = "";

        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
        printAfterFinishing();
    }

    @Test
    public void UT_TC_001_001_002(){
        printTestCaseCode("UT_TC_001_001_002");
        String id = "7862378";

        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(null);
        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(true);
        printAfterFinishing();
    }

    @Test
    public void UT_TC_001_001_003(){
        printTestCaseCode("UT_TC_001_001_003");
        String id = "782378";
        User user = new User();
        user.setUniUuid(id);

        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(user);
        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id已存在");
        printAfterFinishing();
    }

    @Test
    public void UT_TC_001_002_001_001(){
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
    public void UT_TC_001_002_001_002(){
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
    public void UT_TC_001_002_001_003(){
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
    public void UT_TC_001_002_002(){
        String userId= "765432";
        String nickname = "dwdw";
        String avatarUrl = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);
        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(null);
        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }
    @Test
    public void UT_TC_001_002_003(){
        String userId= "test153";
        String nickname = "dwdw";
        String avatarUrl = "dwdw";
        User user = new User();
        user.setUniUuid(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);
        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);
        BaseResponse result = userService.login(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(true);

    }


    @Test
    public void UT_TC_001_003_001(){
        String userId= "";
        BaseResponse result = userService.getUserInfo(userId);
        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void UT_TC_001_003_002(){
        String userId= "765432";
        BaseResponse result = userService.getUserInfo(userId);
        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }
    @Test
    public void UT_TC_001_003_003(){
        String userId= "test153";
        User user = new User();
        user.setUniUuid(userId);
        UserInfo userInfo = new UserInfo(user.getUniUuid(),user.getUniAvatarUrl(),
                user.getUniNickName(),user.getUniSex(),user.getUniIndiSign(),user.getUniIsStu(),
                user.getUniSchool(),user.getUniPhoneNum(),user.getChangeNickName(),user.getChangeAvatar(),
                user.getUniPassPhone(),0,0,0);

        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);
        BaseResponse result = userService.getUserInfo(userId);
        Assertions.assertThat(result.getData()).isEqualTo(userInfo);
    }



    @Test
    public void UT_TC_001_004_001_001(){
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
    public void UT_TC_001_004_001_002(){
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
    public void UT_TC_001_004_001_003(){
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
    public void UT_TC_001_004_001_004(){
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
    public void UT_TC_001_004_002(){
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
    public void UT_TC_001_004_003(){
        String userId = "test153";
        String nickName = "dwdw";
        String avatar = "dwdw";
        String phone = "18372";
        String indiSign = "fefefef";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);
        jsonObject.put(PHONE,phone);
        jsonObject.put(INDI_SIGN,indiSign);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("手机号不符合要求");
    }

    @Test
    public void UT_TC_001_004_004(){
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

        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = userService.updateUserInfo(jsonObject);
        Assertions.assertThat(result.getData()).isEqualTo(true);
    }



    @Test
    public void UT_TC_001_005_001_001(){
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
    public void UT_TC_001_005_001_002(){
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
    public void UT_TC_001_005_001_003(){
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
    public void UT_TC_001_005_002(){
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
    public void UT_TC_001_005_003(){
        String userId = "test153";
        String nickName = "dwdw";
        String avatar = "dwdw";

        User user = new User();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = userService.loginWithUpdate(jsonObject,userId);
        Assertions.assertThat(result.getData()).isEqualTo(true);

    }


    @Test
    public void UT_TC_001_006_001_001(){
        String userId = "";
        int status = 0;
        String school = "2e2e";

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void UT_TC_001_006_001_002(){
        String userId = "2323";
        int status = 0;
        String school = "";

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }


    @Test
    public void UT_TC_001_006_002(){
        String userId = "765432";
        int status = 1;
        String school = "kdjwk";

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }
    @Test
    public void UT_TC_001_006_003_001(){
        String userId = "test153";
        int status = -1;
        String school = "dwdw";

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void UT_TC_001_006_003_002(){
        String userId = "test153";
        int status = 5;
        String school = "dwdw";

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(false);
    }


    @Test
    public void UT_TC_001_006_004(){
        String userId = "test153";
        int status = 2;
        String school = "dwdw";
        User user = new User();
        user.setUniUuid(userId);

        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void UT_TC_001_006_005(){
        String userId = "test153";
        int status = 0;
        String school = "dwdw";
        User user = new User();
        user.setUniUuid(userId);

        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void UT_TC_001_006_006(){
        String userId = "test153";
        int status = 1;
        String school = "dwdw";
        User user = new User();
        user.setUniUuid(userId);

        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        boolean result = userService.upLoadStatus(userId,status,school);
        Assertions.assertThat(result).isEqualTo(true);
    }






    public void printTestCaseCode(String s){
        System.out.println("====================================");
        System.out.println("测试用例编号: "+s);
    }

    public void printAfterFinishing(){
        System.out.println("====================================");
    }

}
