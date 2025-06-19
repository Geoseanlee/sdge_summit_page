package com.sdgs.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice 注解表示这是一个全局的异常处理类
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(Exception.class) 表示这个方法处理所有类型的 Exception
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        // 打印错误堆栈到控制台，方便调试
        e.printStackTrace();
        // 向前端返回一个通用的错误信息
        return Result.error(500, "服务器内部错误，请联系管理员");
    }
}
