package com.uniskare.eureka_skill;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.netflix.discovery.converters.Auto;
import com.uniskare.eureka_skill.controller.OrderController;

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

//remove::end[]

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EurekaSkillApplication.class, MockServletContext.class })
public abstract class OrderRestBase {
    //remove::start[]

    @Autowired
    private WebApplicationContext context;
    @Before
    public void setup() {
//        MockMvcBuilders.webAppContextSetup(this.context).build()
        RestAssuredMockMvc.webAppContextSetup(this.context);
    }
    //remove::end[]
    @After
    public void end(){
        
    }
}