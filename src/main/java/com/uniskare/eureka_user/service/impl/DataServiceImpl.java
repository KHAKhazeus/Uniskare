package com.uniskare.eureka_user.service.impl;

import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.Response.Code;
import com.uniskare.eureka_user.controller.Response.ResponseMessage;
import com.uniskare.eureka_user.dto.DataDTO;
import com.uniskare.eureka_user.entity.Moment;
import com.uniskare.eureka_user.entity.Skill;
import com.uniskare.eureka_user.entity.SkillOrder;
import com.uniskare.eureka_user.entity.UserLikeMoment;
import com.uniskare.eureka_user.repository.*;
import com.uniskare.eureka_user.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class DataServiceImpl implements DataService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    private MomentRepo momentRepo;
    @Autowired
    private UserLikeRepo userLikeRepo;




    @Override
    public BaseResponse getOnesData(String userId) {
        List<Moment> moments = momentRepo.findByUserId(userId);
        List<Skill> skills = skillRepo.findByUserId(userId);
        List<SkillOrder> skillOrders = orderRepo.findAllByUserId(userId);
        List<UserLikeMoment> userLikeMomentList = userLikeRepo.findAllByUserId(userId);

        int skillNum = 0;
        double value = 0;
        int orderNum = 0;
        if(skillOrders != null){
            orderNum = skillOrders.size();
        }

        if(skills != null){
            skillNum = skills.size();
            for(Skill skill:skills){
                List<SkillOrder> skillOrderList = skill.getSkillOrders();
                if(skillOrderList!=null){
                    for(SkillOrder skillOrder:skillOrderList){
                        if(skillOrder.getState() > 3){
                            value+=skillOrder.getValue();
                        }
                    }
                }
            }
        }

        int momentNum = 0;
        int getLikesNum=0;

        if(moments != null){
            momentNum = moments.size();
            for(Moment moment:moments){
                List<UserLikeMoment> userLikeMoments = moment.getUserLikeMoments();
                if(userLikeMoments != null){
                    getLikesNum += userLikeMoments.size();
                }
            }
        }

        int likeNum = 0;
        if(userLikeMomentList!=null){
            likeNum = userLikeMomentList.size();
        }


        DataDTO dataDTO = new DataDTO(value,momentNum,getLikesNum,likeNum,skillNum,orderNum);


        return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                Code.OK,
                Code.NO_ERROR_MESSAGE,
                ResponseMessage.LOGIN_SUCCESS,
                "/user/login",
                dataDTO);
    }




}
