package com.uniskare.eureka_skill.entity.Response;



public class BaseResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    private Object data;

    public BaseResponse(String timestamp,int status,String error,String message,String path,
                        Object data){
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.data = data;
    }
}
