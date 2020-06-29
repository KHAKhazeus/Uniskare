package com.uniskare.eureka_user.controller;

import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// CHECKSTYLE:OFF
/**
 * @author : LK
 * @description :
 */

@RestController
@CrossOrigin //跨域
@RequestMapping("/user") //基路径
public class TokenConroller {

    @GetMapping("/token")
    String getToken(){
        String accessKey = "X6hTCi-g-QkGxc3uE9HHN7g-2Vy64TnU8YlQ020q";
        String secreteKey = "weMcbdosr1Pe6OzqeN-XL5vy6Nj79yvCyPO1Qoco";
        String bucket = "uniskare";

        Auth auth = Auth.create(accessKey,secreteKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }


}
