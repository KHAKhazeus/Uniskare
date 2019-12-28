package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepo extends JpaRepository<Skill,Integer> {
    public List<Skill> findByUserId(String userId);
}
