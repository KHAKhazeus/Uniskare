package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
/**
 * @author : SCH001
 * @description :
 */
public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Skill findBySkillId(int skill_id);
    Page<Skill> findByFullType(String fullType, Pageable pageable);
    Page<Skill> findByFullTypeAndSubtype(String fullType,String subType,Pageable pageable);
}