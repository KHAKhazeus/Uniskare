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


        try {
            Page<SkillDTO> result =  skillService.findAll(PageRequest.of(page, 2));
            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.QUERY_SUCCESS
                    , "/skill/all"
                    , result);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    //save总是返回skill，感觉这里无法判断是不是插入成功，这里一定成功，只能捕捉一下excep，前端可以判断skill格式不对或者网络问题导致的失败
    @PostMapping(path="/insert")
    public BaseResponse insertSkill(@RequestBody Skill skill) {
        try {
            Skill result = skillService.save(skill);
            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.INSERT_SUCCESS
                    , "/skill/insert"
                    , result);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }
    //原api未指明id，保持一样。就是说此时提交的skill必须有id
    @PutMapping(path="/update")
    public BaseResponse updateSkill(@RequestBody Skill skill) {
        try {
            //这里如果没有找到skillId对应的技能的话需要区分返回吗？
            skillService.updateSkill(skill);
            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.UPDATE_SUCCESS
                    , "/skill/update"
                    , null);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }
    @DeleteMapping("/delete/{skillId}")
    public BaseResponse deleteSkill(@PathVariable(value = "skillId") int skillId) {
        //感觉没必要判断在不在，反正deleteById返回的总是空
//        Optional<Skill> skill = skillService.findById(skillId);
//        if(skill.isPresent()) {
//            skillService.deleteById(skillId);
//        }
        try {
            skillService.deleteById(skillId);
            //delete的路径有问题，不过baseResponse到底用哪个？
            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.DELETE_SUCCESS
                    , "/skill/delete"
                    , null);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    //Todo: 要用到like的特性
    @GetMapping("/search")
    public BaseResponse searchSkills(@RequestParam("page") int page,@RequestParam("name") String name) {
        return null;
    }

    //Todo: jpa参数匹配问题
    @GetMapping("all/{fullType}")
    public BaseResponse getSkillByFullType(@PathVariable("fullType") String fullType,@RequestParam("page") int page) {
        try {
            Page<Skill> result = skillService.findByFullType(fullType,PageRequest.of(page,2));

            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.QUERY_SUCCESS
                    , "/skill/all"
                    , result);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    @GetMapping("all/{fullType}/{subType})")
    public BaseResponse getSkillBySubType(@PathVariable("fullType") String fullType, @PathVariable("subType") String subtype,@RequestParam("page") int page) {
        try {
            Page<Skill> result = skillService.findByFullTypeAndSubtype(fullType,subtype,PageRequest.of(page,2));

            BaseResponse baseResponse = new BaseResponse((new Timestamp(System.currentTimeMillis())).toString()
                    , Code.OK
                    , Code.NO_ERROR_MESSAGE
                    , ResponseMessage.QUERY_SUCCESS
                    , "/skill/all"
                    , result);
            return baseResponse;
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.OK, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }
    //Todo：为什么以前要用到UserId
    @RequestMapping("/{skillId")
    public BaseResponse getSkillByskillId(@PathVariable("skillId") int skillId) {
        return null;
    }
}
