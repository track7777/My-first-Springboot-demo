package com.example.demo.enums;

public enum ExceptionEnum {
    SUCCESS("1000", "success"),
    FAIL("1001", "fail"),
    VALIDATE_ERROR("1002","parameter error"),
    UNKNOWN("1003", "unknown error");

    private String code; //错误码
    private String msg; //错误信息

    ExceptionEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
