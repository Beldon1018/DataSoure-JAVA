package com.api.open.controller;

import com.api.open.exception.NoDataException;
import com.api.open.model.ResultModel;
import com.api.open.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 003 on 2018/9/3.
 */
@ControllerAdvice
public class AllControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest req, Exception ex) {
        /*if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            ApiJsonModel apiJsonModel = openMapper.getJsonWithUrl(req.getRequestURI().replaceAll("/", ""));
            if (apiJsonModel != null) {
                return apiJsonModel.getJson();
            }
        }*/
        return ResultUtil.toFail(ex.getMessage(), req.getRequestURL());
    }

    /**
     * 拦截捕捉自定义异常 NoDataException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NoDataException.class)
    public ResultModel myErrorHandler(HttpServletRequest req, NoDataException ex) {
        return ResultUtil.toObject(ex.getCode(), ex.getMsg(), req.getRequestURL());
    }

}
