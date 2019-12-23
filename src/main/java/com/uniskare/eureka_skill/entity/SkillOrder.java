package com.uniskare.eureka_skill.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@Entity
@Table(name = "skill_order", schema = "sedb", catalog = "")
public class SkillOrder {
    private int orderId;
    private int skillId;
    private String userId;
    private Byte state;
    private Timestamp orderTime;
    private double value;

    public SkillOrder(int skillId, String userId, Byte state, Timestamp orderTime, double value) {
        this.skillId = skillId;
        this.userId = userId;
        this.state = state;
        this.orderTime = orderTime;
        this.value = value;
    }



    public SkillOrder() {
    }

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 加上才可以拿到generate的id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "order_time")
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillOrder that = (SkillOrder) o;
        return orderId == that.orderId &&
                skillId == that.skillId &&
                Double.compare(that.value, value) == 0 &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(state, that.state) &&
                Objects.equals(orderTime, that.orderTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, skillId, userId, state, orderTime, value);
    }
}
