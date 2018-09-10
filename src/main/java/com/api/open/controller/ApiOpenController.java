package com.api.open.controller;

import com.api.open.dao.ApiOpenMapper;
import com.api.open.model.ApiJsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户json操作控制器
 * Created by 003 on 2018/9/10.
 */
@RestController
public class ApiOpenController {

    @Autowired
    ApiOpenMapper openMapper;

    /**
     * 获取用户自己存放的JSON，目前通过url标记来取出JSON。
     *
     * @param url 用户自己定义的url标记
     * @return
     */
    @RequestMapping(value = "apiJson")
    public String getTestContent(String url) {
        ApiJsonModel apiJsonModel = openMapper.getJsonWithUrl(url);
        return apiJsonModel.getJson();
    }

}
