package com.uniskare.eureka_user.controller.Response;

import lombok.Data;

/**
 *  @author : LK
 *  @description :
 */


@Data
public class BaseResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    private Object data;

    public BaseResponse(String timestamp,int status,String error,String message,String path,
                        Object data){
        this.timestamp = timestamp;//时间戳
        this.status = status;//状态码
        this.error = error;//错误信息
        this.message = message;//插入成功之类的
        this.path = path;//跳转的url?
        this.data = data;//真实数据
    }

    public BaseResponse(int status, String error,String message, Object data)
    {
        this.status = status;//状态码
        this.error = error;//错误信息
        this.message = message;//插入成功之类的
        this.data = data;//真实数据
    }
}
