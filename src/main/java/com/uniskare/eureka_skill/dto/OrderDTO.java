package com.uniskare.eureka_skill.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author : SCH001
 * @description :
 */

//data transfer object, 面向数据传输, 展示给前端看的类

@Data
@AllArgsConstructor
public class OrderDTO {
    private String skill_cover;
    private double price;
    private String unit;   //
    private String userId;
    private Timestamp order_time;
    private String content;   //
    private String skill_title;

    public OrderDTO()
    {
    }
}
