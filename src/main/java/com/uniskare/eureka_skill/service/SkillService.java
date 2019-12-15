package com.uniskare.eureka_skill.service;

import com.alibaba.fastjson.JSONArray;
import com.uniskare.eureka_skill.entity.Skill;

import java.util.List;

public interface SkillService {
    public List<Skill> findAll();
}
