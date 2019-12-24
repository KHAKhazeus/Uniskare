package com.uniskare.eureka_user;


import com.uniskare.eureka_user.entity.UserPic;
import com.uniskare.eureka_user.repository.UserPicRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EurekaSkillApplicationTests {

    @Autowired
    private UserPicRepo userPicRepo;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testInsertCertification(){
        UserPic userPic = new UserPic();
        userPic.setUserId("111");
        userPic.setPicIndex(0);
        userPic.setUrl("3123123123");

        userPicRepo.save(userPic);

        UserPic userPic1 = new UserPic();
        userPic1.setUserId("111");
        userPic1.setPicIndex(1);
        userPic1.setUrl("23123123");
        userPicRepo.save(userPic1);


    }

}
