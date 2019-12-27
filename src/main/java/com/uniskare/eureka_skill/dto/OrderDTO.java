package com.uniskare.eureka_skill.dto;

import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.entity.SkillOrder;
import com.uniskare.eureka_skill.entity.User;
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
    private BigDecimal price;
    private String unit;   //
    private String userId;
    private Timestamp order_time;
    private String skill_content;   //
    private String skill_title;
    private int status;
    private int orderId;
    private int skillId;

    public OrderDTO()
    {
    }

    public OrderDTO(Skill skill, User customer, SkillOrder order)
    {
        this(
                skill.getCover(), skill.getPrice(), skill.getUnit(),
                customer.getUniUuid(), order.getOrderTime(), skill.getContent(), skill.getTitle(),
                order.getState(), order.getOrderId(), skill.getSkillId()
        );
    }
}
