package com.uniskare.eureka_user;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.Response.ResponseMessage;
import com.uniskare.eureka_user.dto.CertificationDTO;
import com.uniskare.eureka_user.dto.UserInfo;
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.entity.UserPic;
import com.uniskare.eureka_user.repository.UserPicRepo;
import com.uniskare.eureka_user.repository.UserRepo;
import com.uniskare.eureka_user.service.CertificationService;
import com.uniskare.eureka_user.service.UserService;
import net.bytebuddy.asm.Advice;
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
import static com.uniskare.eureka_user.service.Helper.Const.SCHOOL;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CertificationServiceIntegrationTest {

    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_user")
    static class InnerConfig{}

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserPicRepo userPicRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CertificationService certificationService;

    @Test
    public void IT_TC_005_001_001(){
        JSONObject jsonObject = new JSONObject();
        String userId = "";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);



        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_005_001_002(){
        JSONObject jsonObject = new JSONObject();
        String userId = "aaa";
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);



        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void IT_TC_005_001_003(){
        JSONObject jsonObject = new JSONObject();
        String userId = null;
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);



        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(400);
//        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_005_001_004(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);
        List<String> pics = new ArrayList<>();
//        pics.add("wwww");
//        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);


        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("图片数量过少");
    }

    @Test
    public void IT_TC_005_001_005(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        pics.add("we");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);


        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("图片数量过多");
    }

    @Test
    public void IT_TC_005_001_006(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);


        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("学校名不能为空");
    }

    @Test
    public void IT_TC_005_001_007(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);
        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = null;
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);


        BaseResponse result = certificationService.upLoadCertification(jsonObject);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(400);
//        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_005_001_008(){
        JSONObject jsonObject = new JSONObject();
        String userId = "test155";
        User user = new User();
        user.setUniUuid(userId);

        List<String> pics = new ArrayList<>();
        pics.add("wwww");
        pics.add("wwww");
        String school = "tjsc";
        jsonObject.put(USER_ID,userId);
        jsonObject.put(PIC,pics);
        jsonObject.put(SCHOOL,school);

        userRepo.save(user);

        BaseResponse result = certificationService.upLoadCertification(jsonObject);


        List<UserPic> userPics = userPicRepo.findAllByUserId(userId);
        User checkUser = userRepo.findByUniUuid(userId);
        Assertions.assertThat(result.getData()).isEqualTo(true);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(checkUser.getUniIsStu()).isEqualTo(1);
        Assertions.assertThat(checkUser.getUniSchool()).isEqualTo(school);
        Assertions.assertThat(userPics.get(0).getUrl()).isEqualTo("wwww");
        Assertions.assertThat(userPics.get(1).getUrl()).isEqualTo("wwww");
    }

    @Test
    public void IT_TC_005_002_001(){
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




//        Mockito.when(userPicRepo.findAllByUserId(user_1_id)).thenReturn(userPics1);
//        Mockito.when(userPicRepo.findAllByUserId(user_2_id)).thenReturn(userPics2);
        BaseResponse result = certificationService.getCertifications();



        List<CertificationDTO> certificationDTOS = new ArrayList<>();
//        certificationDTOS.add(new CertificationDTO(user1,userPics1));
////        certificationDTOS.add(new CertificationDTO(user2,userPics2));
//
//        Assertions.assertThat(result.getData()).isEqualTo(certificationDTOS);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
    }



    @Test
    public void IT_TC_005_002_002(){
        String user_1_id = "aaa";
//        String user_2_id = "bbb";


        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUniIsStu(1);
        user1.setUniUuid(user_1_id);
//        User user2 = new User();
//        user2.setUniUuid(user_2_id);
        users.add(user1);
//        users.add(user2);

        List<UserPic> userPics1 = new ArrayList<>();
        UserPic userPic_1_1 = new UserPic();
        userPic_1_1.setUrl("aaaaa");
        userPic_1_1.setPicIndex(0);
        userPic_1_1.setUserId(user_1_id);

        UserPic userPic_1_2 = new UserPic();
        userPic_1_2.setUrl("aaaaa");
        userPic_1_2.setPicIndex(1);
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


        userRepo.save(user1);
        userPicRepo.save(userPic_1_1);
        userPicRepo.save(userPic_1_2);


//        Mockito.when(userPicRepo.findAllByUserId(user_2_id)).thenReturn(userPics2);
        BaseResponse result = certificationService.getCertifications();

        List<CertificationDTO> certificationDTO = (List<CertificationDTO>) result.getData();

        certificationDTO.removeIf(certificationDTO1 -> !certificationDTO1.getUser().getUniUuid().equals(user_1_id));

        List<CertificationDTO> certificationDTOS = new ArrayList<>();
        certificationDTOS.add(new CertificationDTO(user1,userPics1));
//        certificationDTOS.add(new CertificationDTO(user2,userPics2));

        Assertions.assertThat(certificationDTO).isEqualTo(certificationDTOS);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
    }


    @Test
    public void IT_TC_005_002_003(){
        String user_1_id = "aaa";
        String user_2_id = "bbb";


        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUniIsStu(1);
        user1.setUniUuid(user_1_id);
        User user2 = new User();
        user2.setUniUuid(user_2_id);
        user2.setUniIsStu(1);
        users.add(user1);
        users.add(user2);

        List<UserPic> userPics1 = new ArrayList<>();
        UserPic userPic_1_1 = new UserPic();
        userPic_1_1.setUrl("aaaaa");
        userPic_1_1.setUserId(user_1_id);
        userPic_1_1.setPicIndex(0);

        UserPic userPic_1_2 = new UserPic();
        userPic_1_2.setUrl("aaaaa");
        userPic_1_2.setUserId(user_1_id);
        userPic_1_2.setPicIndex(1);

        userPics1.add(userPic_1_1);
        userPics1.add(userPic_1_2);


        List<UserPic> userPics2 = new ArrayList<>();
        UserPic userPic_2_1 = new UserPic();
        userPic_2_1.setUrl("aaaaa");
        userPic_2_1.setUserId(user_2_id);
        userPic_2_1.setPicIndex(0);

        UserPic userPic_2_2 = new UserPic();
        userPic_2_2.setUrl("aaaaa");
        userPic_2_2.setUserId(user_2_id);
        userPic_2_2.setPicIndex(1);

        userPics2.add(userPic_2_1);
        userPics2.add(userPic_2_2);

        userRepo.save(user1);
        userPicRepo.save(userPic_1_1);
        userPicRepo.save(userPic_1_2);

        userRepo.save(user2);
        userPicRepo.save(userPic_2_1);
        userPicRepo.save(userPic_2_2);

        BaseResponse result = certificationService.getCertifications();

        List<CertificationDTO> certificationDTO = (List<CertificationDTO>) result.getData();

        certificationDTO.removeIf(certificationDTO1 -> (!certificationDTO1.getUser().getUniUuid().equals(user_1_id)
                &&(!certificationDTO1.getUser().getUniUuid().equals(user_2_id))));

        List<CertificationDTO> certificationDTOS = new ArrayList<>();
        certificationDTOS.add(new CertificationDTO(user1,userPics1));
        certificationDTOS.add(new CertificationDTO(user2,userPics2));

        Assertions.assertThat(certificationDTO).isEqualTo(certificationDTOS);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
    }



    @Test
    public void IT_TC_005_003_001(){

        String userId = "";
        User user = new User();
        user.setUniUuid(userId);




        BaseResponse result = certificationService.acceptCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_005_003_002(){

        String userId = "11111";
        User user = new User();
        user.setUniUuid(userId);




        BaseResponse result = certificationService.acceptCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void IT_TC_005_003_003(){

        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);

        BaseResponse result = certificationService.acceptCertification(userId);

        User check = userRepo.findByUniUuid(userId);

        Assertions.assertThat(result.getData()).isEqualTo(true);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(check.getUniIsStu()).isEqualTo(2);

    }

    @Test
    public void IT_TC_005_003_004(){

        String userId = null;
        User user = new User();
        user.setUniUuid(userId);




        BaseResponse result = certificationService.acceptCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(409);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

    }



    @Test
    public void IT_TC_005_004_001(){

        String userId = "";
        User user = new User();
        user.setUniUuid(userId);



        BaseResponse result = certificationService.denyCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("不合法的id");
    }

    @Test
    public void IT_TC_005_004_002(){

        String userId = "11111";
        User user = new User();
        user.setUniUuid(userId);




        BaseResponse result = certificationService.denyCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(false);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getMessage()).isEqualTo("id不存在");
    }

    @Test
    public void IT_TC_005_004_003(){

        String userId = "test154";
        User user = new User();
        user.setUniUuid(userId);

        userRepo.save(user);


        BaseResponse result = certificationService.denyCertification(userId);
        User check = userRepo.findByUniUuid(userId);
        Assertions.assertThat(result.getData()).isEqualTo(true);
        Assertions.assertThat(result.getStatus()).isEqualTo(200);
        Assertions.assertThat(check.getUniIsStu()).isEqualTo(0);

    }

    @Test
    public void IT_TC_005_004_004(){

        String userId = null;
        User user = new User();
        user.setUniUuid(userId);



        BaseResponse result = certificationService.denyCertification(userId);

        Assertions.assertThat(result.getData()).isEqualTo(null);
        Assertions.assertThat(result.getStatus()).isEqualTo(409);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

    }
}
