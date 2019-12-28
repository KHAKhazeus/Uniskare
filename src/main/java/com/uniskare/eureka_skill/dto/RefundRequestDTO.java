package com.uniskare.eureka_skill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */

@Data
@AllArgsConstructor
//技客拒绝退款的退款申请信息
public class RefundRequestDTO {
    String skill_title;
    int refund_id;
    String refund_reason;
    List<String> refund_images;
    String customer_avatar;
    String customer_name;
    String user_id;
}
