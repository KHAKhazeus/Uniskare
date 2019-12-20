package com.uniskare.eureka_skill.service.impl;

import com.uniskare.eureka_skill.dto.SkillDTO;
import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * @author : Bhy
 * @description ：
 */
@Service
public class SkillServiceImpl implements SkillService {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private SkillRepo skillRepo;

    @Override
    public Skill save(Skill skill) {
        return skillRepo.save(skill);
    }

    @Override
    public Page<SkillDTO> findAll(Pageable pageable) {
        return skillRepo.findAllProjectedBy(pageable);
    }

    @Override
    public Optional<Skill> findById(int skillId) {
        return skillRepo.findById(skillId);
    }

    @Override
    public void deleteById(int skillId) {
        skillRepo.deleteById(skillId);
    }

    /**
     *
     * @param skill
     * @return 1表示有相应技能，0表示没有
     */
    @Override
    public boolean updateSkill(Skill skill) {
        Optional<Skill> oldSkillOpt = skillRepo.findById(skill.getSkillId());
        //看原来的update只update了这些内容
        if(oldSkillOpt.isPresent()) {
            Skill oldSkill = oldSkillOpt.get();
            oldSkill.setTitle(skill.getTitle());
            oldSkill.setContent(skill.getContent());
            oldSkill.setFullType(skill.getFullType());
            oldSkill.setSubtype(skill.getSubtype());
            oldSkill.setSubsubtype(skill.getSubsubtype());
            skillRepo.save(oldSkill);
            return true;
        }
        return false;
    }

    @Override
    public Page<SkillDTO> findByFullType(String fullType, Pageable pageable) {
        return skillRepo.findByFullType(fullType,pageable);
    }

    @Override
    public Page<SkillDTO> findByFullTypeAndSubtype(String fullType, String subtype, Pageable pageable) {
        return skillRepo.findByFullTypeAndSubtype(fullType,subtype,pageable);
    }

}
