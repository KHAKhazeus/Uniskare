package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.StarSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Bhy
 * @description ：
 */
public interface StarSkillRepo extends JpaRepository<StarSkill,Integer> {
    Page<StarSkill> findByStarId(String starId, Pageable pageable);
}
