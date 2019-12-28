package com.uniskare.eureka_skill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.dto.FollowInfo;
import com.uniskare.eureka_skill.entity.Relation;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.repository.FollowRepo;
import com.uniskare.eureka_skill.repository.RelationRepo;
import com.uniskare.eureka_skill.repository.UserRepo;
import com.uniskare.eureka_skill.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.uniskare.eureka_skill.controller.Response.BaseResponse.ErrResponse;
import static com.uniskare.eureka_skill.controller.Response.BaseResponse.SucResponse;
import static com.uniskare.eureka_skill.service.Helper.Const.*;

/**
 * @author : SCH001
 * @description :
 */

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowRepo followRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RelationRepo relationRepo;

    @Override
    public BaseResponse follow(JSONObject body) {
        String fan_id = body.getString(FAN_ID);
        String follow_id = body.getString(FOLLOW_ID);

        try{
            Relation relation = new Relation(follow_id, fan_id);
            followRepo.save(relation);
            return SucResponse(null);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage() + " 是否已经关注过该用户？");
        }

    }

    @Override
    @Modifying
    @Transactional
    public BaseResponse unfollow(JSONObject body) {
        String fan_id = body.getString(FAN_ID);
        String follow_id = body.getString(FOLLOW_ID);

        try{
            followRepo.deleteByFollowIdAndFanId(follow_id, fan_id);
            return SucResponse(null);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse followers(JSONObject body) {
        String user_id = body.getString(USER_ID);
        try{
            List<Relation> relations = followRepo.findByFollowId(user_id);
            List<FollowInfo> followInfos = new ArrayList<>();
            for(Relation relation:relations)
            {
                String fan_id = relation.getFanId();
                User user = userRepo.findByUniUuid(fan_id);
                Relation relation1 = relationRepo.findByFollowIdAndFanId(fan_id,user_id);
                boolean isFollow=false;
                if(relation1!=null){
                    isFollow=true;
                }
                FollowInfo info = new FollowInfo(user.getUniAvatarUrl(),user.getUniNickName(),user.getUniIndiSign(),
                        fan_id,isFollow);

                followInfos.add(info);
            }

            return SucResponse(followInfos);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

    @Override
    public BaseResponse following(JSONObject body) {
        String user_id = body.getString(USER_ID);
        try{
            List<Relation> relations = followRepo.findByFanId(user_id);
            List<FollowInfo> followInfos = new ArrayList<>();
            for(Relation relation:relations)
            {
                String follow_id = relation.getFollowId();
                User user = userRepo.findByUniUuid(follow_id);
                FollowInfo info = new FollowInfo(user.getUniAvatarUrl(),user.getUniNickName(),user.getUniIndiSign(),
                        follow_id,true);

                followInfos.add(info);
            }

            return SucResponse(followInfos);
        }catch (Exception e)
        {
            return ErrResponse(e.getMessage());
        }
    }

}
