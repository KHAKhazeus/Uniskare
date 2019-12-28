package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MomentRepo extends JpaRepository<Moment,Integer> {
    List<Moment> findByUserId(String userId);
}
