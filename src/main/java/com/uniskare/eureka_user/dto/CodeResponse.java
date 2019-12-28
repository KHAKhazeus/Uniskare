package com.uniskare.eureka_user.dto;

import lombok.Data;

/**
 * @author : LK
 * @description :
 */
@Data
public class CodeResponse {
    String session_key;
    String openid;

    public String getOpenid(){
        return openid;
    }
}
