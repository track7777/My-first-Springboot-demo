package com.example.demo.exception;

import com.example.demo.enums.ExceptionEnum;

public class BusinessException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
    private String code;
    private String msg;

    public BusinessException(ExceptionEnum exceptionEnum){
        this.exceptionEnum = exceptionEnum;
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }

    public BusinessException(String code, String msg) {
        super("{code:" + code + ", msg:" + msg + "}");
        this.code = code;
        this.msg = msg;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
