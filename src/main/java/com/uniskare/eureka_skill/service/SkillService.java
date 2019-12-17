package com.uniskare.eureka_skill.service;

import com.uniskare.eureka_skill.entity.Skill;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * @author : Bhy
 * @description ：
 */
public interface SkillService {
    public Skill save(Skill skill);
    public Page<Skill> findAll(Pageable pageable);
    public Optional<Skill> findById(int skillId);
    public void deleteById(int skillId);
    public boolean updateSkill(Skill skill);
}
