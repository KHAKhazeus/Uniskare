package com.uniskare.eureka_skill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RefundDTO {
    int orderId;
    int refundId;
    String skillTitle;
    List<String> images;
    String content;


    public RefundDTO(){

    }
}
