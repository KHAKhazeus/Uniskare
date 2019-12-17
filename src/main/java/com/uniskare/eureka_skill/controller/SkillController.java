package com.uniskare.eureka_skill.controller;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
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

    //TODO 封装返回的data
    @GetMapping(path="/all")
    public Page<Skill> getAllSkills(@RequestParam("page") int page) {

        return skillService.findAll(PageRequest.of(page, 2));
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
            skillService.updateSkill(skill);
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




}
