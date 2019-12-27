package com.uniskare.eureka_skill.controller;


import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //跨域
@RequestMapping("/comm") //基路径
public class RelationController {

    @Autowired
    RelationService relationService;

    @RequestMapping(value = "/relation/{followId}/{fanId}",method = RequestMethod.GET)
    public BaseResponse checkRelationExist(@PathVariable("followId") String followId,@PathVariable("fanId") String fanId){
        return relationService.checkExistRelation(followId,fanId);
    }
}
