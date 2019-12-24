package com.uniskare.eureka_user.controller;


import com.alibaba.fastjson.JSONObject;
import com.uniskare.eureka_user.controller.Response.BaseResponse;
import com.uniskare.eureka_user.service.CertificationService;
import com.uniskare.eureka_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author : LK
 * @description :
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/user") //基路径
public class CertificationController {
    @Autowired
    private UserService userService;

    @Autowired
    private CertificationService certificationService;

    @RequestMapping(value = "/certification/upload",method = RequestMethod.POST)
    public BaseResponse upLoadCertification(@RequestBody JSONObject jsonObject){
        return certificationService.upLoadCertification(jsonObject);
    }
}
