package com.api.open.controller;

import com.api.open.model.ResultModel;
import com.api.open.util.ResultUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局错误处理
 * Created by 003 on 2018/9/3.
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class AllErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    @ResponseBody
    public ResultModel doHandleError(HttpServletRequest req) {
        return ResultUtil.toFail(null);
    }
}
