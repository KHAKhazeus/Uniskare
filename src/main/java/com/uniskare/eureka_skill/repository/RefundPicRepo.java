package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.RefundPic;
import com.uniskare.eureka_skill.entity.RefundPicPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundPicRepo extends JpaRepository<RefundPic, RefundPicPK> {
    public List<RefundPic> findByRefundId(int refundId);
}
