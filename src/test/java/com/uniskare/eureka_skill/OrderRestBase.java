package com.uniskare.eureka_skill;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.netflix.discovery.converters.Auto;
import com.uniskare.eureka_skill.controller.OrderController;

import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.entity.SkillOrder;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.repository.OrderRepo;
import com.uniskare.eureka_skill.repository.SkillRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

//remove::end[]

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EurekaSkillApplication.class, MockServletContext.class })
public abstract class OrderRestBase {
    //remove::start[]

    @Autowired
    private WebApplicationContext context;

    @Autowired
    TestUserRepo testUserRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    TestSkillRepo skillRepo;

    @Before
    public void setup() {
//        MockMvcBuilders.webAppContextSetup(this.context).build()
        RestAssuredMockMvc.webAppContextSetup(this.context);
        insertTestData();
    }

    @Transactional
    public void insertTestData(){
        User user = new User();
        user.setUniUuid("901");
        testUserRepo.save(user);
        Skill skill = new Skill();
        skill.setUserId("901");
        skill.setSkillId(901);
        skillRepo.save(skill);
    }
    //remove::end[]
    @After
    @Transactional
    public void end(){

        List<SkillOrder> deleteOrders = orderRepo.findAllByUserId("901");
        orderRepo.deleteInBatch(deleteOrders);
        List<Skill> deleteSkills = skillRepo.findAllBySkillId(901);
        skillRepo.deleteInBatch(deleteSkills);
        List<User> deleteUsers = testUserRepo.findAllByUniUuid("901");
        testUserRepo.deleteInBatch(deleteUsers);

    }
}