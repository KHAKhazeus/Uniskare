package com.uniskare.eureka_skill.service.impl;


import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.entity.Relation;
import com.uniskare.eureka_skill.repository.RelationRepo;
import com.uniskare.eureka_skill.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RelationServiceImpl implements RelationService {



    @Autowired
    private RelationRepo relationRepo;

    @Override
    public BaseResponse checkExistRelation(String followId, String fanId) {

        boolean result = true;
        Relation relation = relationRepo.findByFollowIdAndFanId(followId,fanId);
        if(relation == null){
            result = false;
        }
        return new BaseResponse(result);
    }
}
