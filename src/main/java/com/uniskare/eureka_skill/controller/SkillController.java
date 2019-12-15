package com.uniskare.eureka_skill.controller;

import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/skill")
public class SkillController {


    @Autowired
    private SkillService skillService;


    @GetMapping(path="/all")
    public Page<Skill> getAllSkills(@RequestParam("page") int page) {
        return skillService.findAll(PageRequest.of(page, 2));
    }

    @PostMapping(path="/insert")
    public Skill insertSkill(@RequestBody Skill skill) {
        return skillService.save(skill);
    }

    @DeleteMapping("/delete/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable(value = "skillId") int skillId) {
        //感觉没必要判断在不在，反正deleteById返回的总是空
//        Optional<Skill> skill = skillService.findById(skillId);
//        if(skill.isPresent()) {
//            skillService.deleteById(skillId);
//        }
        skillService.deleteById(skillId);
        return ResponseEntity.ok().build();
    }



}
