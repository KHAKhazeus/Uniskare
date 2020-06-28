package com.uniskare.eureka_skill;


import com.alibaba.fastjson.JSONObject;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.dto.OrderDTO;
import com.uniskare.eureka_skill.dto.OrderPageDTO;
import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.entity.SkillOrder;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.repository.*;
import com.uniskare.eureka_skill.service.OrderService;
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

import static com.uniskare.eureka_skill.service.Helper.Const.*;


/**
 * @author : SCH001
 * @description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderSercieOrderTest {

    @SpringBootApplication(scanBasePackages = "com.uniskare.eureka_skill")
    static class InnerConfig{}

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepo orderRepo;
    @MockBean
    private SkillRepo skillRepo;
    @MockBean
    private UserRepo userRepo;
    @MockBean
    private RefundRepo refundRepo;
    @MockBean
    private RefundPicRepo refundPicRepo;


//    @Test
//    public void UT_TC_003_001_001_001(){
//        printTestCaseCode("UT_TC_003_001_001_001");
//        String id = "";
//
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, 0);
//        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);
//        printAfterFinishing();
//    }
//
//    @Test
//    public void UT_TC_003_001_001_002(){
//        printTestCaseCode("UT_TC_003_001_001_002");
//        String id = "test";
//
//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, 0);
//        json.put(ORDER_STATUS, "");
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj).isEqualTo(null);
//        printAfterFinishing();
//    }
//
//    @Test
//    public void UT_TC_003_001_001_003(){
//        printTestCaseCode("UT_TC_003_001_001_003");
//        String id = "test";
//
//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, ""); // 页码为空
//        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);
//        printAfterFinishing();
//    }
//
//    @Test
//    public void UT_TC_003_001_002(){
//        printTestCaseCode("UT_TC_003_001_002");
//        // 用户存不存在 在查询订单其实是不用 care 的
//        String id = "no_one";
//
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, 0);
//        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(null); // 用户不存在
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);
//        printAfterFinishing();
//    }
//
//    @Test
//    public void UT_TC_003_001_003(){
//        printTestCaseCode("UT_TC_003_001_003");
//        String id = "test";
//
//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, 0);
//        json.put(ORDER_STATUS, 9);
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj.getOrders().size()).isEqualTo(0);
//        printAfterFinishing();
//    }
//
//    @Test
//    public void UT_TC_003_001_004_001(){
//        printTestCaseCode("UT_TC_003_001_004_001");
//        String id = "test";
//
//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, -1); //
//        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj).isEqualTo(null);
//        printAfterFinishing();
//    }
//
//    @Test
//    public void UT_TC_003_001_004_002(){
//        printTestCaseCode("UT_TC_003_001_004_001");
//        String id = "test";
//
//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, Integer.MAX_VALUE); //
//        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj).isEqualTo(null);
//        printAfterFinishing();
//    }
//
//    @Test
//    public void UT_TC_003_001_005(){
//        printTestCaseCode("UT_TC_003_001_005");
//        String id = "test";
//
//        User u = new User();
//        u.setUniUuid(id);
//        Mockito.when(userRepo.findByUniUuid(id)).thenReturn(u);
//
//        SkillOrder skillOrder1 = new SkillOrder();
//        skillOrder1.setState(ORDER_STATUS_CANCELED);
//        skillOrder1.setOrderId(20);
//        skillOrder1.setUserId(id);
//        int skillId1 = 0;
//        Skill skill1 = new Skill();
//        skill1.setSkillId(skillId1);
//        skillOrder1.setSkillId(skillId1);
//        Mockito.when(skillRepo.findBySkillId(skillId1)).thenReturn(skill1);
//
//        SkillOrder skillOrder2 = new SkillOrder();
//        skillOrder2.setState(ORDER_STATUS_CANCELED);
//        skillOrder2.setOrderId(21);
//        skillOrder1.setUserId(id);
//        int skillId2 = 0;
//        Skill skill2 = new Skill();
//        skill2.setSkillId(skillId2);
//        skillOrder2.setSkillId(skillId2);
//        Mockito.when(skillRepo.findBySkillId(skillId2)).thenReturn(skill2);
//
//        List<SkillOrder> orders = new ArrayList<>();
//        orders.add(skillOrder1);
//        orders.add(skillOrder2);
//
//        Mockito.when(orderRepo.findAllByUserIdAndState(id, ORDER_STATUS_CANCELED)).
//                thenReturn(orders);
//
//        JSONObject json = new JSONObject();
//        json.put(USER_ID, id);
//        json.put(PAGE, 0); //
//        json.put(ORDER_STATUS, ORDER_STATUS_CANCELED);
//
//        BaseResponse result = orderService.getOrdersByState(json);
//
//        OrderPageDTO obj = (OrderPageDTO) (result.getData());
//        Assertions.assertThat(obj.getOrders()).isEqualTo((orderList2dto(orders)));
//        Assertions.assertThat(obj.getTotalPage()).isEqualTo(1);
//
//        printAfterFinishing();
//    }

    // ========================================== OperateOrder ============================================== //
    @Test
    public void UT_TC_003_002_001_001(){
        printTestCaseCode("UT_TC_003_002_001_001");

        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        SkillOrder order = new SkillOrder();
        order.setOrderId(id);
        order.setState(ORDER_STATUS_PLACED);
        Mockito.when(orderRepo.findByOrderId(id)).thenReturn(order);

        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    @Test
    public void UT_TC_003_002_001_002(){
        printTestCaseCode("UT_TC_003_002_001_002");

        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        SkillOrder order = new SkillOrder();
        order.setOrderId(id);
        order.setState(ORDER_STATUS_PLACED);
        Mockito.when(orderRepo.findByOrderId(id)).thenReturn(order);

        BaseResponse result = orderService.operateOrder(json, null);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    @Test
    public void UT_TC_003_002_002(){
        printTestCaseCode("UT_TC_003_002_002");

        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

//        SkillOrder order = new SkillOrder();
//        order.setOrderId(id);
//        order.setState(ORDER_STATUS_PLACED);
        Mockito.when(orderRepo.findByOrderId(id)).thenReturn(null);

        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    @Test
    public void UT_TC_003_002_003_001(){
        printTestCaseCode("UT_TC_003_002_001_002");

        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        SkillOrder order = new SkillOrder();
        order.setOrderId(id);
        order.setState(ORDER_STATUS_PLACED);
        Mockito.when(orderRepo.findByOrderId(id)).thenReturn(order);

        BaseResponse result = orderService.operateOrder(json, (byte) 9);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    @Test
    public void UT_TC_003_002_003_002(){
        printTestCaseCode("UT_TC_003_002_003_002");

        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        SkillOrder order = new SkillOrder();
        order.setOrderId(id);
        order.setState(ORDER_STATUS_PLACED);
        Mockito.when(orderRepo.findByOrderId(id)).thenReturn(order);

        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_TAKEN);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.OPERATION_FAIL);
        printAfterFinishing();
    }

    @Test
    public void UT_TC_003_002_004(){
        printTestCaseCode("UT_TC_003_002_004");

        int id = 999;
        JSONObject json = new JSONObject();
        json.put(ORDER_ID, id);

        SkillOrder order = new SkillOrder();
        order.setOrderId(id);
        order.setState(ORDER_STATUS_PLACED);
        Mockito.when(orderRepo.getOne(id)).thenReturn(order);

        BaseResponse result = orderService.operateOrder(json, ORDER_STATUS_PAID);

        Assertions.assertThat(result.getMessage()).isEqualTo(ResponseMessage.UPDATE_SUCCESS);
        printAfterFinishing();
    }


    public void printTestCaseCode(String s){
        System.out.println("====================================");
        System.out.println("测试用例编号: "+s);
    }

    public void printAfterFinishing(){
        System.out.println("====================================");
    }

    private List<OrderDTO> orderList2dto(List<SkillOrder> skillOrders)
    {
        List<OrderDTO> jsonArray = new ArrayList<>();
        for (SkillOrder skillOrder : skillOrders) {
            Skill skill = skillRepo.findBySkillId(skillOrder.getSkillId());
//                User user = userRepo.getOne(skill.getUserId());


            OrderDTO orderDTO = new OrderDTO(skill.getCover(),skill.getPrice(),skill.getUnit(),skill.getUserId(),skillOrder.getOrderTime(),
                    skill.getContent(),skill.getTitle(),skillOrder.getState(),skillOrder.getOrderId(),skillOrder.getSkillId());


            jsonArray.add(orderDTO);
        }
        return  jsonArray;
    }
}
