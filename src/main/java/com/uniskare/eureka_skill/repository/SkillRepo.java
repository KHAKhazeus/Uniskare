package com.uniskare.eureka_skill.repository;


import com.uniskare.eureka_skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface SkillRepo extends JpaRepository<Skill,Integer>{

}
