package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.dto.SkillDTO;
import com.uniskare.eureka_skill.entity.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;

/**
 * @author : Bhy
 * @description ：
 */
public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Page<SkillDTO> findByStatus(Pageable pageable,Byte status);
    Page<Skill> findByStatusOrderByDate(Pageable pageable, Byte stauts);
    Skill findBySkillId(int skill_id);
    Page<SkillDTO> findByFullTypeAndStatus(String fullType, Pageable pageable,Byte status);
    Page<SkillDTO> findByFullTypeAndSubtypeAndStatus(String fullType,String subType,Pageable pageable,Byte status);
    Page<SkillDTO> findByTitleLikeAndStatus(String title,Pageable pageable,Byte status);
    Page<SkillDTO> findByUserId(String userId,Pageable pageable);

}