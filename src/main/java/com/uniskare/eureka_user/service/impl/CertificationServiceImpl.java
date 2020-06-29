package com.uniskare.eureka_user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.controller.Response.Code;
import com.uniskare.eureka_user.controller.Response.ResponseMessage;
import com.uniskare.eureka_user.dto.CertificationDTO;
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.entity.UserPic;
import com.uniskare.eureka_user.repository.UserPicRepo;
import com.uniskare.eureka_user.repository.UserRepo;
import com.uniskare.eureka_user.service.CertificationService;
import com.uniskare.eureka_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.uniskare.eureka_user.service.Helper.Const.*;
@Service
@Transactional
public class CertificationServiceImpl implements CertificationService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserPicRepo userPicRepo;

    @Autowired
    private UserService userService;



    @Override
    public BaseResponse upLoadCertification(JSONObject jsonObject) {
        try{
            String userId = jsonObject.getString(USER_ID);
            JSONArray jsonArray = jsonObject.getJSONArray(PIC);
            String school = jsonObject.getString(SCHOOL);
            String[] imageArray = new String[jsonArray.size()];
            for(int i =0;i<jsonArray.size();i++){
                imageArray[i] = jsonArray.getString(i);
            }
            Boolean judge = userService.upLoadStatus(userId,1,school);
            if(!judge){
                throw new Exception();
            }

            UserPic userPic = new UserPic();
            userPic.setUserId(userId);
            userPic.setPicIndex(0);
            userPic.setUrl(imageArray[0]);
            userPicRepo.save(userPic);
            UserPic userPic1 = new UserPic();
            userPic1.setUserId(userId);
            userPic1.setPicIndex(1);
            userPic1.setUrl(imageArray[1]);
            userPicRepo.save(userPic1);

            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.QUERY_SUCCESS,
                    "/user/getUserInfo",
                    null);
        }catch (Exception e){
            System.out.println(e.toString());
            return new BaseResponse(
                    null, Code.BAD_REQUEST, e.toString(),null, null, null
            );
        }
    }

    @Override
    public BaseResponse getCertifications() {
        try{
            List<CertificationDTO> certificationDTOS = new ArrayList<>();
            List<User> users = userRepo.findAllByUniIsStu(1);
            if(users != null){
                for(User user:users){
                    List<UserPic> userPics = userPicRepo.findAllByUserId(user.getUniUuid());
                    CertificationDTO certificationDTO = new CertificationDTO(user,userPics);
                    certificationDTOS.add(certificationDTO);
                }
            }
            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.QUERY_SUCCESS,
                    "/user/getUserInfo",
                    certificationDTOS);
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.CONFLICT, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    @Override
    public BaseResponse acceptCertification(String userId) {
        try{
            User user = userRepo.findByUniUuid(userId);
            userPicRepo.deleteAllByUserId(userId);
            user.setUniIsStu(2);
            userRepo.save(user);
            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.UPDATE_SUCCESS,
                    "/user/getUserInfo",
                    null);
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.CONFLICT, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }

    @Override
    public BaseResponse denyCertification(String userId) {
        try{
            User user = userRepo.findByUniUuid(userId);
            userPicRepo.deleteAllByUserId(userId);
            user.setUniIsStu(0);
            userRepo.save(user);
            return new BaseResponse((new Timestamp(System.currentTimeMillis())).toString(),
                    Code.OK,
                    Code.NO_ERROR_MESSAGE,
                    ResponseMessage.UPDATE_SUCCESS,
                    "/user/getUserInfo",
                    null);
        }
        catch (Exception e)
        {
            return new BaseResponse(Code.CONFLICT, e.toString(), ResponseMessage.OPERATION_FAIL, null);
        }
    }


}
