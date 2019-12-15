package com.uniskare.eureka_skill.controller;

import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/skill")
public class SkillController {


    @Autowired
    private SkillService skillService;

    @GetMapping(path="/all")
    public Iterable<Skill> findAll() {
        return skillService.findAll();
    }
//    @RequestMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }
}
