package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
public interface MomentRepo extends JpaRepository<Moment, Integer> {
    List<Moment> findAllByUserId(String user_id);
    Moment findByMomentId(int mom_id);
}
