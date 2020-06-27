package com.uniskare.eureka_user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.UserController;
import com.uniskare.eureka_user.dto.CodeResponse;
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.service.UserService;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import sun.lwawt.macosx.CPrinterDevice;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerUnitTest {

    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_user")
    static class InnerConfig{}

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;


    @Mock
    private UserService userService;

    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void UT_TC_001_002_001() throws IOException {

        String code = "";
        String params = "appid=" + "wxc9c276e49f2795b7" +"&secret="+"cd8c771af0d123028d7d9801b15c10b7"+"&js_code="+code+"&grant_type=authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?"+params;

        String mockResponse = "aa";
        CodeResponse mockCodeResponse = new CodeResponse();
        mockCodeResponse.setOpenid("1111");
        mockCodeResponse.setSession_key("1111");


        Mockito.when(restTemplate.getForObject(url,String.class)).thenReturn(mockResponse);

        try {

            Mockito.when(objectMapper.readValue(mockResponse,CodeResponse.class)).thenReturn(mockCodeResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Mockito.when(userService.register(any())).thenReturn(null);
        BaseResponse result = userController.verityLoginCode(code);
        Assertions.assertThat(result.getData()).isEqualTo(null);
    }

    @Test
    public void UT_TC_001_002_002() throws IOException {

        String code = "wdwdwd";
        String params = "appid=" + "wxc9c276e49f2795b7" +"&secret="+"cd8c771af0d123028d7d9801b15c10b7"+"&js_code="+code+"&grant_type=authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?"+params;

        String mockResponse = "aa";
        CodeResponse mockCodeResponse = new CodeResponse();
        mockCodeResponse.setOpenid("1111");
        mockCodeResponse.setSession_key("1111");

        Mockito.when(restTemplate.getForObject(url,String.class)).thenReturn(mockResponse);
        try {
            Mockito.when(objectMapper.readValue(mockResponse,CodeResponse.class)).thenReturn(mockCodeResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mockito.when(userService.register(any())).thenReturn(null);

        BaseResponse result = userController.verityLoginCode(code);
        Assertions.assertThat(result.getData()).isEqualTo(mockCodeResponse);
    }

}
