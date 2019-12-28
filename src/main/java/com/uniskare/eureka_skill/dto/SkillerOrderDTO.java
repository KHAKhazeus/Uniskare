package com.uniskare.eureka_skill.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkillerOrderDTO {
    String skillTitle;
    double price;
    String unit;
    int orderId;
    String userAvatar;
    String userId;
    String userNickName;
    int status;
}
