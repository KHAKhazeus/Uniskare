package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.SkillOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
public interface OrderRepo extends JpaRepository<SkillOrder, Integer>{
    public List<SkillOrder> findAllByUserIdAndState(String userId, Byte status);
    public List<SkillOrder> findAllByUserId(String user_id);
    public List<SkillOrder> findAllByState(Byte status);

    public SkillOrder findByOrderId(int order_id);
}
