package com.uniskare.eureka_skill.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.dto.MomentShow;
import com.uniskare.eureka_skill.entity.Moment;
import com.uniskare.eureka_skill.entity.MomentPic;
import com.uniskare.eureka_skill.entity.User;
import com.uniskare.eureka_skill.entity.UserLikeMomentPK;
import com.uniskare.eureka_skill.repository.MomentPicRepo;
import com.uniskare.eureka_skill.repository.MomentRepo;
import com.uniskare.eureka_skill.repository.UserLikeMomRepo;
import com.uniskare.eureka_skill.repository.UserRepo;
import com.uniskare.eureka_skill.service.Helper.BackendException;
import com.uniskare.eureka_skill.service.Helper.Const;
import com.uniskare.eureka_skill.service.Helper.MyPageHelper;
import com.uniskare.eureka_skill.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.uniskare.eureka_skill.service.Helper.Const.*;

/**
 * @author : SCH001
 * @description :
 */
@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    MomentRepo momentRepo;
    @Autowired
    MomentPicRepo momentPicRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserLikeMomRepo userLikeMomRepo;

    @Override
    public BaseResponse insertMoment(JSONObject body) {
        try{
            Moment moment = getOrNewMoment(body, true);
//            momentRepo.save(moment);
            return new BaseResponse(moment.getMomentId());
        }catch (Exception e)
        {
            return new BaseResponse(Code.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public BaseResponse updateMoment(JSONObject body) {
        try {
            getOrNewMoment(body, false);
            return new BaseResponse(null);
        } catch (Exception e) {
            return new BaseResponse(Code.BAD_REQUEST,e.getMessage());
        }
    }


    @Override
    public BaseResponse getOnesMoments(String user_id, int page, String watcher_id) {
        try{
            List<Moment> moments = momentRepo.findAllByUserId(user_id);

            User user = userRepo.findByUniUuid(user_id);
            List<MomentShow> momentShows = transformMom(moments, user, true,watcher_id);

            List<MomentShow> ret =MyPageHelper.convert2Page(momentShows, page);

            return new BaseResponse(ret);
        }catch (Exception e)
        {
            return new BaseResponse(null,e.getMessage());
        }
    }

    /**
     *
     * @param moments
     * @param user 如果是获取某个人的动态，就传那个人进来，
     *             如果是获取**朋友圈**动态，传一个null
     * @return
     */
    private List<MomentShow> transformMom(List<Moment> moments, User user, boolean justone,
                                          String watcher_id
    )
    {
        //按照动态时间排序，时间后的在前
        moments.sort((x,y)-> y.getTime().compareTo(x.getTime()));
        List<MomentShow> ret = new ArrayList<>();
        //User MomentPic

        for (Moment moment : moments) {
            //如果不只是一个人
            if (!justone) {
                user = userRepo.findByUniUuid(moment.getUserId());
            }
            int mom_id = moment.getMomentId();

            List<MomentPic> pics = momentPicRepo.findAllByMomentId(mom_id);
            List<String> picsOnly = pics.stream()
                    .sorted(Comparator.comparingInt(MomentPic::getPindex)) // 按照索引排序
                    .map(MomentPic::getUrl).collect(Collectors.toList());//只拿url

            int likeNum = userLikeMomRepo.findAllByMomentId(mom_id).size();
            MomentShow momentShow = new MomentShow(
                    moment, user, picsOnly, likeNum
            );

            boolean isLike = userLikeMomRepo.findByUserIdAndMomentId(watcher_id, mom_id) != null;
            momentShow.setIslike(isLike);

            ret.add(momentShow);
        }


        return ret;
    }

    @Override
    public BaseResponse getAllMoments(String watcher_id, int page) {
        try{
            List<Moment> moments = momentRepo.findAll();

            List<MomentShow> momentShows = transformMom(moments, null, false,watcher_id);

            List<MomentShow> ret =MyPageHelper.convert2Page(momentShows, page);

            return new BaseResponse(ret);
        }catch (Exception e)
        {
            return new BaseResponse(null, e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteMoment(int mom_id) {
        try{
            //图片和动态
            momentPicRepo.deleteAllByMomentId(mom_id);
            momentRepo.deleteById(mom_id);
        }catch (Exception e)
        {
            return new BaseResponse(null, e.getMessage());
        }
        return new BaseResponse(null);
    }


    //新建或者获取一个Moment
    private Moment getOrNewMoment(JSONObject body, boolean insert) throws BackendException {
        String user_id = body.getString(USER_ID);
        String content = body.getString(CONTENT);
        Byte can_see = body.getByte(CAN_SEE);
        Timestamp timestamp = body.getTimestamp(MOM_TIME);

        Moment moment;
        if(!insert)
        {
            int mom_id = body.getIntValue(MOM_ID);
            moment = new Moment(mom_id,user_id,content,can_see,timestamp);
        }else {
            moment = new Moment(user_id,content,can_see,timestamp);
        }

        JSONArray pics = body.getJSONArray(PICTURES);
        insertPictures(pics, moment.getMomentId());

        momentRepo.save(moment);

        return moment;
    }

    //插入图片
    private void insertPictures(JSONArray pics, int mom_id) throws BackendException {
        if (pics == null)
        {
            return;
        }

        List<MomentPic> temp = new ArrayList<>();
        for(int i = 0;i<pics.size();i++)
        {
            String url = (String)pics.get(i);
            MomentPic momentPic = new MomentPic(mom_id,i,url);
            temp.add(momentPic);
        }

        try{
            //删除原始图片
            momentPicRepo.deleteAllByMomentId(mom_id);
            //保存
            for(MomentPic momentPic:temp)
            {
                momentPicRepo.save(momentPic);
            }
        }catch (Exception e)
        {
            throw new BackendException("图片插入或者更新出错");
        }
    }
}
