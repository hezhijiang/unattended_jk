package com.gez.woodware.util;



import com.gez.woodware.entity.basics.RetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 返回的Map对象会被@ResponseBody注解转换为JSON数据返回
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RetResponse handleException(HttpServletRequest request, Exception e){
        log.error(request.getRequestURI(),e);

        return new RetResponse(false,e.getMessage());

    }
}

