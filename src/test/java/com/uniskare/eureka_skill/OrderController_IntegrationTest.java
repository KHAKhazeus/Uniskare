package com.uniskare.eureka_skill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.dto.OrderDTO;
import com.uniskare.eureka_skill.dto.OrderPageDTO;
import com.uniskare.eureka_skill.entity.Refund;
import com.uniskare.eureka_skill.entity.SkillOrder;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.repository.OrderRepo;
import com.uniskare.eureka_skill.service.OrderService;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import static com.uniskare.eureka_skill.service.Helper.Const.*;
import static com.uniskare.eureka_skill.service.Helper.Const.ORDER_STATUS_CANCELED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author : SCH001
 * @description :
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrderController_IntegrationTest {
    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_skill")
    static class InnerConfig{}

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepo orderRepo;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String URL_004_001 = "/orders/orders_info";

    OrderPageDTO getOrderDTO(Object data)
    {
        String str = JSON.toJSONString(data);
        return JSONObject.parseObject(str, OrderPageDTO.class);
    }
//
    BaseResponse mockMvcPostJsonFormat(String url,JSONObject json, int expectedCode) throws Exception {
        String requestJson = json.toJSONString();

        String httpResult = mockMvc.perform(post(url).
                contentType(APPLICATION_JSON).content(requestJson))
                .andExpect(status().is(expectedCode))
                .andReturn().getResponse().getContentAsString();
        if(expectedCode == 404) return  null;
        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        return  result;
    }

    // ====================================================================================== //

    @Test
    public void IT_TC_004_001_001_001() throws Exception {
        printTestCaseCode("IT_TC_004_001_001_001");
        String id = "";

        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, 0);
        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);


        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO(result.getData());
        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);


        printAfterFinishing();
    }
    // =================================================== //
    // 测试数据
    public int TEST_SKILL = 22;
    public int TEST_ORDER_PLACED = 24;
    public String TEST_USER = "oEHOK5Tzznlf9O8gWenDShxCmz78";

    @Test
    public void IT_TC_004_001_001_002() throws Exception {
        printTestCaseCode("IT_TC_004_001_001_002");
        String id = TEST_USER;


        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, 0);
        json.put(ORDER_STATUS, "");

        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO (result.getData());
        Assertions.assertThat(obj).isEqualTo(null);
        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_001_001_003() throws Exception {
        printTestCaseCode("IT_TC_004_001_001_003");
        String id = TEST_USER;


        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, ""); // 页码为空
        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO (result.getData());
        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);
        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_001_002() throws Exception {
        printTestCaseCode("IT_TC_004_001_002");
        // 用户存不存在 在查询订单其实是不用 care 的
        String id = "no_one";

        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, 0);
        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(null); // 用户不存在

        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO(result.getData());
        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);
        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_001_003() throws Exception {
        printTestCaseCode("IT_TC_004_001_003");
        String id = TEST_USER;

//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, 0);
        json.put(ORDER_STATUS, 9); // 状态存在但是不合法

        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO (result.getData());
        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);
        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_001_004_001() throws Exception {
        printTestCaseCode("IT_TC_004_001_004_001");
        String id = TEST_USER;


        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, -1); // 页码超过范围
        json.put(ORDER_STATUS, ORDER_STATUS_PLACED);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO (result.getData());
        Assertions.assertThat(obj).isEqualTo(null);
        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_001_004_002() throws Exception {
        printTestCaseCode("IT_TC_004_001_004_001");
        String id = TEST_USER;

//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, Integer.MAX_VALUE); //
        json.put(ORDER_STATUS, ORDER_STATUS_PLACED);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO (result.getData());
        Assertions.assertThat(obj).isEqualTo(null);
        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_001_005() throws Exception {
        printTestCaseCode("IT_TC_004_001_005");
        String id = TEST_USER;

        JSONObject json = new JSONObject();
        json.put(USER_ID, id);
        json.put(PAGE, 0); //
        json.put(ORDER_STATUS, -1);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_001, json, 200);

        OrderPageDTO obj = getOrderDTO (result.getData());
//        Assertions.assertThat(obj.getOrders()).isEqualTo((orderList2dto(orders)));
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.OK);
        Assertions.assertThat(result.getError()).isEqualTo(Code.NO_ERROR_MESSAGE);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.QUERY_SUCCESS);

        // 不一定成立，因为在测试时我只插入了两条订单，请根据数据库情况更改或注释
//        Assertions.assertThat(obj.getTotalPage()).isEqualTo(1);

        printAfterFinishing();
    }

    // =======================================  pay Order  ===========================================//

    String URL_004_002 = "/orders/pay";
    // id 为空
    @Test
    public void IT_TC_004_002_001() throws Exception {
        printTestCaseCode("IT_TC_004_002_001");

//        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, "");

//        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_002, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    // id 不存在
    @Test
    public void IT_TC_004_002_002() throws Exception {
        printTestCaseCode("IT_TC_004_002_002");

        int id = 999; // 订单不存在
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);


        BaseResponse result = mockMvcPostJsonFormat(URL_004_002, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }


    int TEST_ORDER_TO_PAY = 25;
    int TEST_ORDER_TO_TAKE = 26;
    int TEST_ORDER_TO_CONFIRM = 27;
    int TEST_ORDER_TO_FINISH = 28;
    int TEST_ORDER_TO_CANCEL = 24;// 什么状态都可以用来测试 正确的取消订单操作（）
    @Test
    public void IT_TC_004_002_003() throws Exception {
        printTestCaseCode("IT_TC_004_002_003");

        int id = TEST_ORDER_TO_PAY;
//        BeforeOperateOrder(id);

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

//        SkillOrder order = new SkillOrder();
//        order.setOrderId(id);
//        order.setState(ORDER_STATUS_PLACED);
//        Mockito.when(orderRepo.getOne(id)).thenReturn(order);

//        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_002, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.UPDATE_SUCCESS);
        SkillOrder order = orderRepo.findByOrderId(id);
        Assertions.assertThat(order!=null
                && order.getState().equals(ORDER_STATUS_PAID)).isEqualTo(true);
        printAfterFinishing();
    }


    private  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // =============================================  New Order  =========================================== //

    String URL_004_003 = "/orders/new_order";
    @Test
    public void IT_TC_004_003_001_001() throws Exception {
        printTestCaseCode("IT_TC_004_003_001_001");

        // 变量声明
        String user_id = ""; //  用户不存在
        int skill_id = TEST_SKILL;
        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:10").getTime());
        double val = 90.00;

        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, order_time);
        json.put(ORDER_VALUE, val);

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
//        Assertions.assertThat((int)result.getData()).isEqualTo(0); //

        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_003_001_002() throws Exception {
        printTestCaseCode("IT_TC_004_003_001_002");

        // 变量声明
        String user_id = "test";
//        int skill_id = 2;
        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:10").getTime());
        double val = 90.00;

        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, ""); //
        json.put(ORDER_TIME, order_time);
        json.put(ORDER_VALUE, val);

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
//        Assertions.assertThat((int)result.getData()).isEqualTo(0); //

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_003_001_003() throws Exception {
        printTestCaseCode("IT_TC_004_003_001_003");

        // 变量声明
        String user_id = "oEHOK5Tzznlf9O8gWenDShxCmz78";
        int skill_id = 2;
//        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:30").getTime());
        double val = 90.00;


        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, "");
        json.put(ORDER_VALUE, val);

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
//        Assertions.assertThat((int)result.getData()).isEqualTo(0); //

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_003_001_004() throws Exception {
        printTestCaseCode("IT_TC_004_003_001_004");

        // 变量声明
        String user_id = "oEHOK5Tzznlf9O8gWenDShxCmz78";
        int skill_id = 2;
        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:10").getTime());
//        double val = 90.00;

        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, order_time);
        json.put(ORDER_VALUE, "");

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
//        Assertions.assertThat((int)result.getData()).isEqualTo(0); //

        printAfterFinishing();
    }


    int TEST_SKILL_NOT_EXIST = -1111;
    String TEST_USER_NOT_EXIST = "no one like me";

    @Test
    public void IT_TC_004_003_002() throws Exception {
        printTestCaseCode("IT_TC_004_003_002");

        // 变量声明
        String user_id = "test";
        int skill_id = TEST_SKILL_NOT_EXIST; // 不存在的 skill
        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:10").getTime());
        double val = 90.00;


        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, order_time);
        json.put(ORDER_VALUE, val);

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
//        Assertions.assertThat((int)result.getData()).isEqualTo(0); //

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_003_003() throws Exception {
        printTestCaseCode("IT_TC_004_003_003");

        // 变量声明
        String user_id = TEST_USER_NOT_EXIST;
        int skill_id = 2;
        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:10").getTime());
        double val = 90.00;


        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, order_time);
        json.put(ORDER_VALUE, val);

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
//        Assertions.assertThat((int)result.getData()).isEqualTo(0); //

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_003_004() throws Exception {
        printTestCaseCode("IT_TC_004_003_004");

        // 变量声明
        String user_id = "oEHOK5Tzznlf9O8gWenDShxCmz78";
        int skill_id = 2;
//        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:30").getTime());
        double val = 90.00;


        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, "2019-12-17 10"); // 时间格式错误
        json.put(ORDER_VALUE, val);

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
//        Assertions.assertThat((int)result.getData()).isEqualTo(0); //

        printAfterFinishing();
    }


    private  SkillOrder fakeOrder(String user_id, int skill_id, Timestamp order_time, double val)
    {
        SkillOrder order = new SkillOrder();
        order.setState(ORDER_STATUS_PLACED);
        order.setOrderId(0);//.........................................................................................................

        order.setUserId(user_id);
        order.setSkillId(skill_id);
        order.setOrderTime(order_time);
        order.setValue(val);

//        Mockito.when(orderRepo.findByOrderId(0)).thenReturn(order);

        return order;
    }
    @Test
    public void IT_TC_004_003_005() throws Exception {
        printTestCaseCode("IT_TC_004_003_005");

        // 变量声明
        String user_id = TEST_USER;
        int skill_id = 2;
        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:10").getTime());
        double val = 90.00;


        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, order_time);
        json.put(ORDER_VALUE, val);

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.INSERT_SUCCESS);

        // 检查参数
        int new_order_id = (int)result.getData();
        SkillOrder order = orderRepo.getOne(new_order_id);
        Assertions.assertThat(order.getUserId()).isEqualTo(user_id);
        Assertions.assertThat(order.getSkillId()).isEqualTo(skill_id);
        Assertions.assertThat(order.getOrderTime()).isEqualTo(order_time);
        Assertions.assertThat(order.getValue()).isEqualTo(val);

        printAfterFinishing();
    }

    public void IT_TC_004_003_006() throws Exception {
        printTestCaseCode("IT_TC_004_003_006");

        // 变量声明
        String user_id = TEST_USER;
        int skill_id = 2;
        Timestamp order_time = new Timestamp(simpleDateFormat.parse("2019-12-17 10:30:10").getTime());
//        double val = 90.00;


        JSONObject json = new JSONObject();
        json.put(USER_ID, user_id);
        json.put(SKILL_ID, skill_id);
        json.put(ORDER_TIME, order_time);
        json.put(ORDER_VALUE, "&90"); // 不合法的格式

//        BaseResponse result = orderService.newOrder(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_003, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

        printAfterFinishing();
    }


    String URL_004_004 = "/orders/refund";

    // ======================================  Apply Refund  ===================================== //
    @Test
    public void IT_TC_004_004_001_001() throws Exception {
        printTestCaseCode("IT_TC_004_004_001_001");

        // 变量声明
//        int order_id = 0;
        List<String> pics = new ArrayList<>();
        pics.add("1.jpg");
        pics.add("2.jpg");
        String refund_time = "2019-12-17 10:30:10";
        String content = "不是很满意，这个差评我给定了！";

//        fakeOrder("", order_id, null, -1.0);

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, "");
        json.put(PICS, pics);
        json.put(TIME, refund_time);
        json.put(CONTENT, content);

//        BaseResponse result = orderService.applyRefund(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_004, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_004_001_002() throws Exception {
        printTestCaseCode("IT_TC_004_004_001_002");

        // 变量声明
        int order_id = 0;
//        List<String> pics = new ArrayList<>();
//        pics.add("1.jpg");
//        pics.add("2.jpg");
        String refund_time = "2019-12-17 10:30:10";
        String content = "不是很满意，这个差评我给定了！";

        fakeOrder("", order_id, null, -1.0);

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, order_id);
        json.put(PICS, "");
        json.put(TIME, refund_time);
        json.put(CONTENT, content);

//        BaseResponse result = orderService.applyRefund(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_004, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_004_001_003() throws Exception {
        printTestCaseCode("IT_TC_004_004_001_003");

        // 变量声明
        int order_id = 0;
        List<String> pics = new ArrayList<>();
        pics.add("1.jpg");
        pics.add("2.jpg");
//        String refund_time = "2019-12-17 10:30:10";
        String content = "不是很满意，这个差评我给定了！";

        fakeOrder("", order_id, null, -1.0);

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, order_id);
        json.put(PICS, pics);
        json.put(TIME, "");
        json.put(CONTENT, content);

//        BaseResponse result = orderService.applyRefund(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_004, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_004_001_004() throws Exception {
        printTestCaseCode("IT_TC_004_004_001_004");

        // 变量声明
        int order_id = 0;
        List<String> pics = new ArrayList<>();
        pics.add("1.jpg");
        pics.add("2.jpg");
        String refund_time = "2019-12-17 10:30:10";
//        String content = "不是很满意，这个差评我给定了！";

//        fakeOrder("", order_id, null, -1.0);

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, order_id);
        json.put(PICS, pics);
        json.put(TIME, refund_time);
        json.put(CONTENT, null);

//        BaseResponse result = orderService.applyRefund(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_004, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_004_002() throws Exception {
        printTestCaseCode("IT_TC_004_004_002");

        // 变量声明
        int order_id = -1;
        List<String> pics = new ArrayList<>();
        pics.add("1.jpg");
        pics.add("2.jpg");
        String refund_time = "2019-12-17 10:30:10";
        String content = "不是很满意，这个差评我给定了！";

//        fakeOrder("", order_id, null, -1.0);
//        Mockito.when(orderRepo.findByOrderId(order_id)).thenReturn(null);

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, order_id);
        json.put(PICS, pics);
        json.put(TIME, refund_time);
        json.put(CONTENT, content);

//        BaseResponse result = orderService.applyRefund(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_004, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_004_003() throws Exception {
        printTestCaseCode("IT_TC_004_004_003");

        // 变量声明
        int order_id = 0;
        List<String> pics = new ArrayList<>();
        pics.add("1.jpg");
        pics.add("2.jpg");
        String refund_time = "2019-12-17 10"; // 时间格式
        String content = "不是很满意，这个差评我给定了！";

//        fakeOrder("", order_id, null, -1.0);

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, order_id);
        json.put(PICS, pics);
        json.put(TIME, refund_time);
        json.put(CONTENT, content);

//        BaseResponse result = orderService.applyRefund(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_004, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);

        printAfterFinishing();
    }


    int TEST_ORDER_TO_REFUND = 24;
    @Test
    public void IT_TC_004_004_004() throws Exception {
        printTestCaseCode("IT_TC_004_004_004");

        // 变量声明
        int order_id = TEST_ORDER_TO_REFUND;
        List<String> pics = new ArrayList<>();
        pics.add("1.jpg");
        pics.add("2.jpg");
        String refund_time = "2019-12-17 10:30:10";
        String content = "不是很满意，这个差评我给定了！";


        JSONObject json = new JSONObject();
        json.put(ORDER_ID, order_id);
        json.put(PICS, pics);
        json.put(TIME, refund_time);
        json.put(CONTENT, content);

//        BaseResponse result = orderService.applyRefund(json);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_004, json, 200);


        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.INSERT_SUCCESS);
        SkillOrder order = orderRepo.findByOrderId(order_id);
        Assertions.assertThat(order.getState()).isEqualTo(ORDER_STATUS_REFUND);

        printAfterFinishing();
    }

    BaseResponse mockMvcGetApply(String url, String page, int exceptedCode) throws Exception {
        String httpResult = mockMvc.perform(get(url)
                .param(PAGE, page)) //???
                .andExpect(status().is(exceptedCode))
                .andReturn().getResponse().getContentAsString();

        if(exceptedCode != 200) return null;
        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        return  result;
    }
    BaseResponse mockMvcGetApply(String url, int page, int exceptedCode) throws Exception {
        String httpResult = mockMvc.perform(get(url)
                .param(PAGE, JSON.toJSONString(page))) //???
                .andExpect(status().is(exceptedCode))
                .andReturn().getResponse().getContentAsString();

        if(exceptedCode != 200) return  null;
        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        return  result;
    }

    ////////////////////////////////////////      ///////////////////////////////////////////////
    public String URL_004_005(String user_id)
    {
        return "/orders/"+user_id+"/apply";
    }
    // =======================================  get Apply  ========================================= //
    @Test
    public void IT_TC_004_005_001_001() throws Exception {
        printTestCaseCode("IT_TC_004_005_001_001");

        // 变量声明
        String user_id = "";
        int page = 0;
        Boolean is_refund = true;


        JSONObject json = new JSONObject();
        json.put(PAGE, page);

//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_005(user_id), page, 404);

//        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_005_001_002() throws Exception {
        printTestCaseCode("IT_TC_004_005_001_003");

        // 变量声明
        String user_id = TEST_USER;
        int page = 0;
        Boolean is_refund = null;

//        fakeUser(user_id);

//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_005(user_id), "", 400);
//        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_005_002() throws Exception {
        printTestCaseCode("IT_TC_004_005_002");

        // 变量声明
        String user_id = TEST_USER_NOT_EXIST;
        int page = 0;
        Boolean is_refund = true;

//        Mockito.when(userRepo.findByUniUuid(user_id)).thenReturn(null);

//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_005(user_id), page, 200);
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_005_003_001() throws Exception {
        printTestCaseCode("IT_TC_004_005_003_001");

        // 变量声明
        String user_id = TEST_USER;
        int page = -1;
        Boolean is_refund = true;


//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_005(user_id), page, 200);
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_005_003_002() throws Exception {
        printTestCaseCode("IT_TC_004_005_003_001");

        // 变量声明
        String user_id = TEST_USER;
        int page = 999999;
        Boolean is_refund = true;


//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_005(user_id), page, 200);
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_005_004() throws Exception {
        printTestCaseCode("IT_TC_004_005_004");

        // 变量声明
        String user_id = TEST_USER;
        int page = 0;
        Boolean is_refund = true;


//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_005(user_id), page, 200);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.QUERY_SUCCESS);

        printAfterFinishing();
    }

    String URL_004_006(int refundId)
    {
        return URL_004_006(JSON.toJSONString(refundId));
    }
    String URL_004_006(String refundId)
    {
        return "/orders/"+refundId+"/info";
    }

    BaseResponse mockGetRefundInfo(String url, int exceptedCode) throws Exception {
        String httpResult = mockMvc.perform(get(url)
//                .param(PAGE, JSON.toJSONString(page))) //???
        ).andExpect(status().is(exceptedCode))
                .andReturn().getResponse().getContentAsString();

        Reader reader = new StringReader(httpResult);
        BaseResponse result = objectMapper.readValue(reader,BaseResponse.class);
        return  result;
    }

    @Test
    public void IT_TC_004_006_001() throws Exception {
        printTestCaseCode("IT_TC_004_006_001");

        // 变量声明
        int refund_id = -1;

//        BaseResponse result = orderService.getRefundInfo(refund_id);
        BaseResponse result = mockGetRefundInfo(URL_004_006(refund_id), 200);
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }


    int TEST_REFUND = 60;
    @Test
    public void IT_TC_004_006_002() throws Exception {
        printTestCaseCode("IT_TC_004_006_001");

        // 变量声明
        int refund_id = TEST_REFUND;


//        BaseResponse result = orderService.getRefundInfo(refund_id);
        BaseResponse result = mockGetRefundInfo(URL_004_006(refund_id), 200);
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.OK);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.QUERY_SUCCESS);
//        Assertions.assertThat((RefundDTO)result.getData()).isEqualTo(orderService)

        printAfterFinishing();
    }

    // =====================================  vii confirm order  ==================================== //
// ==================================================================================//


    String URL_004_007 = "/orders/confirm";
    // id 为空
    @Test
    public void IT_TC_004_007_001() throws Exception {
        printTestCaseCode("IT_TC_004_007_001");

//        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, "");

//        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_007, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    // id 不存在
    @Test
    public void IT_TC_004_007_002() throws Exception {
        printTestCaseCode("IT_TC_004_007_002");

        int id = 999; // 订单不存在
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);


        BaseResponse result = mockMvcPostJsonFormat(URL_004_007, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_007_003() throws Exception {
        printTestCaseCode("IT_TC_004_007_003");

        int id = TEST_ORDER_TO_CONFIRM;

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_007, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.UPDATE_SUCCESS);
        SkillOrder order = orderRepo.findByOrderId(id);
        Assertions.assertThat(order!=null
                && order.getState().equals(ORDER_STATUS_CONFIRMED)).isEqualTo(true);
        printAfterFinishing();
    }

    String URL_004_008 = "/orders/take_order";
    // id 为空
    @Test
    public void IT_TC_004_008_001() throws Exception {
        printTestCaseCode("IT_TC_004_008_001");

//        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, "");

//        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_008, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    // id 不存在
    @Test
    public void IT_TC_004_008_002() throws Exception {
        printTestCaseCode("IT_TC_004_008_002");

        int id = 999; // 订单不存在
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);


        BaseResponse result = mockMvcPostJsonFormat(URL_004_008, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_008_003() throws Exception {
        printTestCaseCode("IT_TC_004_008_003");

        int id = TEST_ORDER_TO_TAKE;

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_008, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.UPDATE_SUCCESS);
        SkillOrder order = orderRepo.findByOrderId(id);
        Assertions.assertThat(order!=null
                && order.getState().equals(ORDER_STATUS_TAKEN)).isEqualTo(true);
        printAfterFinishing();
    }

    String URL_004_009 = "/orders/cancel";
    // id 为空
    @Test
    public void IT_TC_004_009_001() throws Exception {
        printTestCaseCode("IT_TC_004_009_001");

//        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, "");

//        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_009, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    // id 不存在
    @Test
    public void IT_TC_004_009_002() throws Exception {
        printTestCaseCode("IT_TC_004_009_002");

        int id = 999; // 订单不存在
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);


        BaseResponse result = mockMvcPostJsonFormat(URL_004_009, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_009_003() throws Exception {
        printTestCaseCode("IT_TC_004_009_003");

        int id = TEST_ORDER_TO_CANCEL;

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_009, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.UPDATE_SUCCESS);
        SkillOrder order = orderRepo.findByOrderId(id);
        Assertions.assertThat(order!=null
                && order.getState().equals(ORDER_STATUS_CANCELED)).isEqualTo(true);
        printAfterFinishing();
    }


    String URL_004_010 = "/orders/review";
    // id 为空
    @Test
    public void IT_TC_004_010_001() throws Exception {
        printTestCaseCode("IT_TC_004_010_001");

//        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, "");

//        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);
        BaseResponse result = mockMvcPostJsonFormat(URL_004_010, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    // id 不存在
    @Test
    public void IT_TC_004_010_002() throws Exception {
        printTestCaseCode("IT_TC_004_010_002");

        int id = 999; // 订单不存在
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);


        BaseResponse result = mockMvcPostJsonFormat(URL_004_010, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_010_003() throws Exception {
        printTestCaseCode("IT_TC_004_010_003");

        int id = TEST_ORDER_TO_FINISH;

        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        BaseResponse result = mockMvcPostJsonFormat(URL_004_010, json, 200);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.UPDATE_SUCCESS);
        SkillOrder order = orderRepo.findByOrderId(id);
        Assertions.assertThat(order!=null
                && order.getState().equals(ORDER_STATUS_FINISHED)).isEqualTo(true);
        printAfterFinishing();
    }

    ////////////////////////////////////////      ///////////////////////////////////////////////
    public String URL_004_011(String user_id)
    {
        return "/orders/"+user_id+"/refund_request";
    }
    // =======================================  get refund  ========================================= //
    @Test
    public void IT_TC_004_011_001_001() throws Exception {
        printTestCaseCode("IT_TC_004_011_001_001");

        // 变量声明
        String user_id = "";
        int page = 0;
        Boolean is_refund = true;


        JSONObject json = new JSONObject();
        json.put(PAGE, page);

//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        String url = URL_004_011(user_id);
        BaseResponse result = mockMvcGetApply(url, page, 404);

//        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_011_001_002() throws Exception {
        printTestCaseCode("IT_TC_004_011_001_003");

        // 变量声明
        String user_id = TEST_USER;
        int page = 0;
        Boolean is_refund = null;

//        fakeUser(user_id);

//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_011(user_id), "", 400);
//        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_011_002() throws Exception {
        printTestCaseCode("IT_TC_004_011_002");

        // 变量声明
        String user_id = TEST_USER_NOT_EXIST;
        int page = 0;
        Boolean is_refund = true;

//        Mockito.when(userRepo.findByUniUuid(user_id)).thenReturn(null);

//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_011(user_id), page, 200);
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }


    @Test
    public void IT_TC_004_011_003_001() throws Exception {
        printTestCaseCode("IT_TC_004_011_003_001");

        // 变量声明
        String user_id = TEST_USER;
        int page = -1;
        Boolean is_refund = true;


//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_011(user_id), page, 200);
//        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_011_003_002() throws Exception {
        printTestCaseCode("IT_TC_004_011_003_001");

        // 变量声明
        String user_id = TEST_USER;
        int page = 999999;
        Boolean is_refund = true;


//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_011(user_id), page, 200);
        Assertions.assertThat(result.getStatus()).isEqualTo(Code.BAD_REQUEST);

        printAfterFinishing();
    }

    @Test
    public void IT_TC_004_011_004() throws Exception {
        printTestCaseCode("IT_TC_004_011_004");

        // 变量声明
        String user_id = TEST_USER;
        int page = 0;
        Boolean is_refund = true;


//        BaseResponse result = orderService.getOrderRequestDTOs(user_id, page, is_refund);
        BaseResponse result = mockMvcGetApply(URL_004_011(user_id), page, 200);
        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.QUERY_SUCCESS);

        printAfterFinishing();
    }

    public void printTestCaseCode(String s){
        System.out.println("====================================");
        System.out.println("测试用例编号: "+s);
    }

    public void printAfterFinishing(){
        System.out.println("====================================");
    }

}
