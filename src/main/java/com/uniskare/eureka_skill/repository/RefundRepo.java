package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundRepo extends JpaRepository<Refund,Integer> {
    Refund findByOrderId(int orderId);
    Refund findByRefundId(int refundId);
}
