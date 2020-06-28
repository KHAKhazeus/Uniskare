package com.uniskare.eureka_user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.UserController;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.DataInput;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static com.uniskare.eureka_user.service.Helper.Const.*;
import static com.uniskare.eureka_user.service.Helper.Const.AVATAR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIntegrationTest {

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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void IT_TC_002_001_001() throws Exception {
        String id = "";

        String httpResult = mockMvc.perform(post("/user/getLoginCode")
                .param("code",id))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);


//        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(false);

    }

    @Test
    public void IT_TC_002_001_002() throws Exception {

        String id = "7862378";



        String httpResult = mockMvc.perform(post("/user/getLoginCode")
                .param("code",id))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);


//        BaseResponse result = userService.register(id);
        Assertions.assertThat(result.getData()).isEqualTo(false);

    }



    @Test
    public void IT_TC_002_002_001_001() throws Exception {
        String userId= "";
        String nickname = "dwdw";
        String avatarUrl = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_002_002_001_002() throws Exception {
        String userId= "test154";
        String nickname = "";
        String avatarUrl = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("昵称不能为空");
    }

    @Test
    public void IT_TC_002_002_001_003() throws Exception {
        String userId= "test154";
        String nickname = "test1";
        String avatarUrl = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("头像不能为空");
    }

    @Test
    public void IT_TC_002_002_002() throws Exception {
        String userId= "765432";
        String nickname = "dwdw";
        String avatarUrl = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }
    @Test
    public void IT_TC_002_002_003() throws Exception {
        String userId= "oEHOK5WdGlU4fD9K_1YrZDT9pBIA";
        String nickname = "dwdw";
        String avatarUrl = "dwdw";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(true);
    }

    @Test
    public void IT_TC_002_002_004() throws Exception {
        String userId= "test154";
        String nickname = "dwdw";
        String avatarUrl = "dwdw";
        User user = new User();
        user.setChangeNickName((byte)1);
        user.setChangeAvatar((byte)1);
        user.setUniUuid(userId);
        userRepo.save(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USER_ID,userId);
        jsonObject.put(NICK_NAME,nickname);
        jsonObject.put(AVATAR,avatarUrl);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(true);
    }


    @Test
    public void IT_TC_002_003_001() throws Exception {
        String userId= "";

        String httpResult = mockMvc.perform(get("/user/information/"+userId))
                .andExpect(status().is(404))
                .andReturn().getResponse().getContentAsString();

//        Reader reader = new StringReader(httpResult);
//        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
//
//        Assertions.assertThat(result.getData()).isEqualTo(null);
//        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_002_003_002() throws Exception {
        String userId= "765432";
        String httpResult = mockMvc.perform(get("/user/information/"+userId))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void IT_TC_002_003_003() throws Exception {
        String userId= "test154";
        User user = new User();
        user.setUniUuid(userId);

        UserInfo userInfo = new UserInfo(user.getUniUuid(),user.getUniAvatarUrl(),
                user.getUniNickName(),user.getUniSex(),user.getUniIndiSign(),user.getUniIsStu(),
                user.getUniSchool(),user.getUniPhoneNum(),user.getChangeNickName(),user.getChangeAvatar(),
                user.getUniPassPhone(),0,0,0);

        userRepo.save(user);

        String httpResult = mockMvc.perform(get("/user/information/"+userId))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        String convertBridge = JSON.toJSONString(result.getData());
        UserInfo userInfo1 = JSONObject.parseObject(convertBridge,UserInfo.class);

        Assertions.assertThat(userInfo1).isEqualTo(userInfo);
    }


    @Test
    public void IT_TC_002_003_004() throws Exception {
        String userId= "test154";
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


        String httpResult = mockMvc.perform(get("/user/information/"+userId))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);

        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        String convertBridge = JSON.toJSONString(result.getData());
        UserInfo userInfo1 = JSONObject.parseObject(convertBridge,UserInfo.class);


        Assertions.assertThat(userInfo1).isEqualTo(userInfo);
    }





    @Test
    public void IT_TC_002_004_001_001() throws Exception {
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);


        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_002_004_001_002() throws Exception {
        String userId = "test154";
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("昵称不能为空");
    }

    @Test
    public void IT_TC_001_004_001_003() throws Exception {
        String userId = "test154";
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("头像不能为空");
    }

    @Test
    public void IT_TC_002_004_001_004() throws Exception {
        String userId = "test154";
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("手机号不能为空");
    }

    @Test
    public void IT_TC_002_004_002() throws Exception {
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void IT_TC_002_004_003() throws Exception {
        String userId = "test154";
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("手机号不符合要求");
    }

    @Test
    public void IT_TC_002_004_004() throws Exception {
        String userId = "test154";
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(true);
    }

    @Test
    public void IT_TC_002_004_005() throws Exception {
        String userId = "test154";
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

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(true);
    }



    @Test
    public void IT_TC_002_005_001_001() throws Exception {
        String userId = "";
        String nickName = "kjwd";
        String avatar = "qdqwd";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/"+userId+"/login/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(404))
                .andReturn().getResponse().getContentAsString();

//        Reader reader = new StringReader(httpResult);
//        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
//        Assertions.assertThat(result.getData()).isEqualTo(false);
//        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_002_005_001_002() throws Exception {
        String userId = "test154";
        String nickName = "";
        String avatar = "wdwdwwd";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/"+userId+"/login/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("昵称不能为空");
    }

    @Test
    public void IT_TC_002_005_001_003() throws Exception {
        String userId = "test154";
        String nickName = "kjwd";
        String avatar = "";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/"+userId+"/login/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("头像不能为空");
    }



    @Test
    public void IT_TC_002_005_002() throws Exception {
        String userId = "765432";
        String nickName = "kjwd";
        String avatar = "wdwdwwd";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/"+userId+"/login/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }
    @Test
    public void IT_TC_002_005_003() throws Exception {
        String userId = "test154";
        String nickName = "dwdw";
        String avatar = "dwdw";

        User user = new User();
        user.setUniUuid(userId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        userRepo.save(user);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/"+userId+"/login/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(true);

    }

    @Test
    public void IT_TC_002_005_004() throws Exception {
        String userId = "test154";
        String nickName = "dwdw";
        String avatar = "dwdw";

        User user = new User();
        user.setUniUuid(userId);
        user.setChangeAvatar((byte) 1);
        user.setChangeNickName((byte) 1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NICK_NAME,nickName);
        jsonObject.put(AVATAR,avatar);

        userRepo.save(user);

        String requestJson = jsonObject.toJSONString();

        String httpResult = mockMvc.perform(post("/user/"+userId+"/login/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        Assertions.assertThat(result.getData()).isEqualTo(true);

    }


}
