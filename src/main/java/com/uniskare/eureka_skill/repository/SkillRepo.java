package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : SCH001
 * @description :
 */
public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Skill findBySkillId(int skill_id);
}
