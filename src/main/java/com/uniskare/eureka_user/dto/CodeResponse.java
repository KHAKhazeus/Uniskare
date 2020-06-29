package com.uniskare.eureka_user.dto;
// CHECKSTYLE:OFF
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
