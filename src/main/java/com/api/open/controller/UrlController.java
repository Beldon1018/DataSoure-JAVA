package com.api.open.controller;

import com.api.open.dao.ApiOpenMapper;
import com.api.open.model.ApiJsonModel;
import com.api.open.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 获取项目全部URL
 * Created by 003 on 2018/9/10.
 */
@Controller
@RequestMapping("/*")
public class UrlController {
    @Autowired
    ApiOpenMapper openMapper;

    @Autowired
    WebApplicationContext applicationContext;

    @GetMapping("/getAllUrl")
    @ResponseBody
    public List<String> getAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()) {
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String url : patterns) {
                urlList.add(url);
            }
        }
        return urlList;
    }

    /**
     * 优先级低，可以被绝对路径替代，用来返回用户JSON
     */
    @GetMapping("/*")
    @ResponseBody
    public Object getApiJson(HttpServletRequest req) {
        ApiJsonModel apiJsonModel = openMapper.getJsonWithUrl(req.getRequestURI().replaceAll("/", ""));
        if (apiJsonModel != null) {
            return apiJsonModel.getJson();
        }
        return ResultUtil.toFail("404 Not Found", req.getRequestURL());
    }

}
