package com.uniskare.eureka_skill.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.dto.SkillDTO;
import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
/**
 * @author : Bhy
 * @description ：
 */
@RestController
@RequestMapping(path="/skill")
public class SkillController {


    @Autowired
    private SkillService skillService;

    @GetMapping(path="/all")
    public BaseResponse getAllSkills(@RequestParam("page") int page) {

        return skillService.findAll(page);
    }

    @PostMapping(path="/insert")
    public BaseResponse insertSkill(@RequestBody Skill skill) {
        return skillService.save(skill);
    }
    //原api未指明id，保持一样。就是说此时提交的skill必须有id
    @PutMapping(path="/update")
    public BaseResponse updateSkill(@RequestBody Skill skill) {
        return skillService.updateSkill(skill);
    }
    @DeleteMapping("/delete/{skillId}")
    public BaseResponse deleteSkill(@PathVariable(value = "skillId") int skillId) {
        return skillService.deleteById(skillId);
    }


    @GetMapping("all/{fullType}")
    public BaseResponse getSkillByFullType(@PathVariable("fullType") String fullType,@RequestParam("page") int page) {
        return skillService.findByFullType(fullType,page);
    }

    @GetMapping("all/{fullType}/{subType})")
    public BaseResponse getSkillBySubType(@PathVariable("fullType") String fullType, @PathVariable("subType") String subtype,@RequestParam("page") int page) {
        return skillService.findByFullTypeAndSubtype(fullType,subtype,page);
    }
    //Todo：为什么以前要用到UserId
    @RequestMapping("/{skillId")
    public BaseResponse getSkillByskillId(@PathVariable("skillId") int skillId) {
        return null;
    }

    //Todo: 要用到like的特性
    @GetMapping("/search")
    public BaseResponse searchSkills(@RequestParam("page") int page,@RequestParam("name") String name) {
        return null;
    }


}
