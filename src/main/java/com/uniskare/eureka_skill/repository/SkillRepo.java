package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.dto.SkillDTO;
import com.uniskare.eureka_skill.entity.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
/**
 * @author : Bhy
 * @description ：
 */
public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Page<SkillDTO> findAllProjectedBy(Pageable pageable);
    Skill findBySkillId(int skill_id);
    Page<SkillDTO> findByFullType(String fullType, Pageable pageable);
    Page<SkillDTO> findByFullTypeAndSubtype(String fullType,String subType,Pageable pageable);
}