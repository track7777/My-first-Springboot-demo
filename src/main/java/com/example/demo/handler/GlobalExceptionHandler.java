package com.example.demo.handler;

import com.example.demo.enums.ExceptionEnum;
import com.example.demo.exception.BusinessException;
import com.example.demo.response.ResultResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice //全局异常处理
public class GlobalExceptionHandler {
    /* 业务异常 */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(BusinessException e){
        return ResultResponse.error(e.getExceptionEnum());
    }
    /* 系统异常 */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultResponse exceptionHandler(Exception e){
        return ResultResponse.error(ExceptionEnum.UNKNOWN);
    }

}
