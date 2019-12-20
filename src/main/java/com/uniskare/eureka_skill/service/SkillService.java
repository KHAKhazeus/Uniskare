package com.uniskare.eureka_skill.service;

import com.uniskare.eureka_skill.dto.SkillDTO;
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
    public Page<SkillDTO> findAll(Pageable pageable);
    public Optional<Skill> findById(int skillId);
    public void deleteById(int skillId);
    public boolean updateSkill(Skill skill);
    public Page<SkillDTO> findByFullType(String fullType, Pageable pageable);
    public Page<SkillDTO> findByFullTypeAndSubtype(String fullType, String subtype, Pageable pageable);
}
