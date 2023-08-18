package com.example.demo.response;

import com.example.demo.enums.ExceptionEnum;
import lombok.Data;

@Data //lombok的注解，可以自动将自定义对象转化为json对象返回
public class ResultResponse {
    //状态码，状态信息以及具体描述
    private String code;
    private String msg;
    private Object data;
    public ResultResponse(){
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
    //成功时，data为操作的相关数据
    public static ResultResponse success(Object data){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(ExceptionEnum.SUCCESS.getCode());
        resultResponse.setMsg(ExceptionEnum.SUCCESS.getMsg());
        resultResponse.setData(data);
        return resultResponse;
    }
    public static ResultResponse error(ExceptionEnum exceptionEnum){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(exceptionEnum.getCode());
        resultResponse.setMsg(exceptionEnum.getMsg());
        resultResponse.setData(null);
        return resultResponse;
    }
}
