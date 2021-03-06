package com.yonyou.zbs.controller;

import com.yonyou.zbs.vo.RestResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ZbsExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e) {
        return RestResultVO.error(e.getMessage());
    }
}
