package com.uniskare.eureka_user;


import com.uniskare.eureka_user.controller.UserController;
import com.uniskare.eureka_user.entity.UserPic;
import com.uniskare.eureka_user.repository.UserPicRepo;
import com.uniskare.eureka_user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EurekaSkillApplicationTests {

    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_user")
    static class InnerConfig { }

    @Autowired
    UserService userService;

    @MockBean
    private UserController userController;


}
