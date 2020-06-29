package com.uniskare.eureka_user;


import com.alibaba.fastjson.JSONObject;
import com.sun.corba.se.impl.interceptors.PICurrent;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.Response.ResponseMessage;
import com.uniskare.eureka_user.dto.CertificationDTO;
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.entity.UserPic;
import com.uniskare.eureka_user.repository.UserPicRepo;
import com.uniskare.eureka_user.repository.UserRepo;
import com.uniskare.eureka_user.service.CertificationService;
import com.uniskare.eureka_user.service.UserService;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.uniskare.eureka_user.service.Helper.Const.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CertificationServiceUnitTest {
    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_user")
    static class InnerConfig{}

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private UserPicRepo userPicRepo;

    @MockBean
    private UserService userService;

    @Autowired
    private CertificationService certificationService;


    @Test
    public void UT_TC_004_001_001(){
        JSONObject jsonObject = new JSONObject();
        String userId = "";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void UT_TC_004_001_002(){
        JSONObject jsonObject = new JSONObject();
        String userId = "aaa";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void UT_TC_004_001_003(){
        JSONObject jsonObject = new JSONObject();
        String userId = null;
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(400);
//        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void UT_TC_004_001_004(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        List<String> pics = new ArrayList<>();
//        pics.add("wwww");
//        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("图片数量过少");
    }

    @Test
    public void UT_TC_004_001_005(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        pics.add("we");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("图片数量过多");
    }

    @Test
    public void UT_TC_004_001_006(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("学校名不能为空");
    }

    @Test
    public void UT_TC_004_001_007(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = null;
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(400);
//        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void UT_TC_004_001_008(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        Mockito.when(userPicRepo.save(any())).thenReturn(true);
        Mockito.when(userService.upLoadStatus(anyString(),anyInt(),anyString())).thenReturn(true);
        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(true);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
    }

    @Test
    public void UT_TC_004_002_001(){
//        String user_1_id = "aaa";
//        String user_2_id = "bbb";


        List<User> users = new ArrayList<>();
//        User user1 = new User();
//        user1.setUniUuid(user_1_id);
////        User user2 = new User();
////        user2.setUniUuid(user_2_id);
////        users.add(user1);
////        users.add(user2);
//
//        List<UserPic> userPics1 = new ArrayList<>();
//        UserPic userPic_1_1 = new UserPic();
//        userPic_1_1.setUrl("aaaaa");
//        userPic_1_1.setUserId(user_1_id);
//
//        UserPic userPic_1_2 = new UserPic();
//        userPic_1_2.setUrl("aaaaa");
//        userPic_1_2.setUserId(user_1_id);
//
//        userPics1.add(userPic_1_1);
//        userPics1.add(userPic_1_2);


//        List<UserPic> userPics2 = new ArrayList<>();
//        UserPic userPic_2_1 = new UserPic();
//        userPic_2_1.setUrl("aaaaa");
//        userPic_2_1.setUserId(user_2_id);
//
//        UserPic userPic_2_2 = new UserPic();
//        userPic_2_2.setUrl("aaaaa");
//        userPic_2_2.setUserId(user_2_id);

//        userPics2.add(userPic_2_1);
//        userPics2.add(userPic_2_2);



        Mockito.when(userRepo.findAllByUniIsStu(1)).thenReturn(users);
//        Mockito.when(userPicRepo.findAllByUserId(user_1_id)).thenReturn(userPics1);
//        Mockito.when(userPicRepo.findAllByUserId(user_2_id)).thenReturn(userPics2);
        BaseResponse result = certificationService.getCertifications();

        List<CertificationDTO> certificationDTOS = new ArrayList<>();
//        certificationDTOS.add(new CertificationDTO(user1,userPics1));
//        certificationDTOS.add(new CertificationDTO(user2,userPics2));

        Assertions.assertThat(result.getData()).isEqualTo(certificationDTOS);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
    }



    @Test
    public void UT_TC_004_002_002(){
        String user_1_id = "aaa";
//        String user_2_id = "bbb";


        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUniUuid(user_1_id);
//        User user2 = new User();
//        user2.setUniUuid(user_2_id);
        users.add(user1);
//        users.add(user2);

        List<UserPic> userPics1 = new ArrayList<>();
        UserPic userPic_1_1 = new UserPic();
        userPic_1_1.setUrl("aaaaa");
        userPic_1_1.setUserId(user_1_id);

        UserPic userPic_1_2 = new UserPic();
        userPic_1_2.setUrl("aaaaa");
        userPic_1_2.setUserId(user_1_id);

        userPics1.add(userPic_1_1);
        userPics1.add(userPic_1_2);


//        List<UserPic> userPics2 = new ArrayList<>();
//        UserPic userPic_2_1 = new UserPic();
//        userPic_2_1.setUrl("aaaaa");
//        userPic_2_1.setUserId(user_2_id);
//
//        UserPic userPic_2_2 = new UserPic();
//        userPic_2_2.setUrl("aaaaa");
//        userPic_2_2.setUserId(user_2_id);

//        userPics2.add(userPic_2_1);
//        userPics2.add(userPic_2_2);



        Mockito.when(userRepo.findAllByUniIsStu(1)).thenReturn(users);
        Mockito.when(userPicRepo.findAllByUserId(user_1_id)).thenReturn(userPics1);
//        Mockito.when(userPicRepo.findAllByUserId(user_2_id)).thenReturn(userPics2);
        BaseResponse result = certificationService.getCertifications();

        List<CertificationDTO> certificationDTOS = new ArrayList<>();
        certificationDTOS.add(new CertificationDTO(user1,userPics1));
//        certificationDTOS.add(new CertificationDTO(user2,userPics2));

        Assertions.assertThat(result.getData()).isEqualTo(certificationDTOS);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
    }


    @Test
    public void UT_TC_004_002_003(){
        String user_1_id = "aaa";
        String user_2_id = "bbb";


        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUniUuid(user_1_id);
        User user2 = new User();
        user2.setUniUuid(user_2_id);
        users.add(user1);
        users.add(user2);

        List<UserPic> userPics1 = new ArrayList<>();
        UserPic userPic_1_1 = new UserPic();
        userPic_1_1.setUrl("aaaaa");
        userPic_1_1.setUserId(user_1_id);

        UserPic userPic_1_2 = new UserPic();
        userPic_1_2.setUrl("aaaaa");
        userPic_1_2.setUserId(user_1_id);

        userPics1.add(userPic_1_1);
        userPics1.add(userPic_1_2);


        List<UserPic> userPics2 = new ArrayList<>();
        UserPic userPic_2_1 = new UserPic();
        userPic_2_1.setUrl("aaaaa");
        userPic_2_1.setUserId(user_2_id);

        UserPic userPic_2_2 = new UserPic();
        userPic_2_2.setUrl("aaaaa");
        userPic_2_2.setUserId(user_2_id);

        userPics2.add(userPic_2_1);
        userPics2.add(userPic_2_2);



        Mockito.when(userRepo.findAllByUniIsStu(1)).thenReturn(users);
        Mockito.when(userPicRepo.findAllByUserId(user_1_id)).thenReturn(userPics1);
        Mockito.when(userPicRepo.findAllByUserId(user_2_id)).thenReturn(userPics2);
        BaseResponse result = certificationService.getCertifications();

        List<CertificationDTO> certificationDTOS = new ArrayList<>();
        certificationDTOS.add(new CertificationDTO(user1,userPics1));
        certificationDTOS.add(new CertificationDTO(user2,userPics2));

        Assertions.assertThat(result.getData()).isEqualTo(certificationDTOS);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
    }



    @Test
    public void UT_TC_004_003_001(){

        String userId = "";
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = certificationService.acceptCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void UT_TC_004_003_002(){

        String userId = "11111";
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(null);

        BaseResponse result = certificationService.acceptCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void UT_TC_004_003_003(){

        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = certificationService.acceptCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(true);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);

    }

    @Test
    public void UT_TC_004_003_004(){

        String userId = null;
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = certificationService.acceptCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(409);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

    }



    @Test
    public void UT_TC_004_004_001(){

        String userId = "";
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = certificationService.denyCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void UT_TC_004_004_002(){

        String userId = "11111";
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(null);

        BaseResponse result = certificationService.denyCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void UT_TC_004_004_003(){

        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = certificationService.denyCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(true);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);

    }

    @Test
    public void UT_TC_004_004_004(){

        String userId = null;
        User user = new User();
        user.setUniUuid(userId);


        Mockito.when(userRepo.findByUniUuid(userId)).thenReturn(user);

        BaseResponse result = certificationService.denyCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(409);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

    }





}
