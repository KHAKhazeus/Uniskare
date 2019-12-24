package com.uniskare.eureka_skill.service.Helper;

/**
 * @author : SCH001
 * @description :
 */
public class Const {
    public final static String USER_ID = "user_id";
    public final static String OTHER_ID = "other_id";

    public final static String CAN_SEE = "can_see";
    public final static String MOM_TIME = "time";

    public final static String CONTENT = "content";
    public final static String DATE = "date";

    public final static String PICTURES = "pictures";
    public final static String MOM_ID = "moment_id";

    public final static String ORDER_STATUS = "state";
    public final static String ORDER_TIME = "order_time";
    public final static String ORDER_ID = "order_id";
    public final static String ORDER_VALUE = "value";

    public final static String SKILL_ID = "skill_id";

    public final static int NUM_PER_PAGE = 10;

    //订单的状态， enum不太好用
    public final static Byte ORDER_STATUS_CANCELED = 0; // 已取消
    public final static Byte ORDER_STATUS_PLACED = 1; //(订单初始状态)下单(PLACE AN ORDER)未付款
    public final static Byte ORDER_STATUS_PAID = 2; // 用户付款 & 技客未接单
    public final static Byte ORDER_STATUS_TAKEN = 3; //技客接单 & 用户未确认
    public final static Byte ORDER_STATUS_CONFIRMED = 4; //用户确认 未评论
    public final static Byte ORDER_STATUS_FINISHED = 5; // 用户评论，订单完成
}
