package com.api.open.controller;

import com.api.open.dao.TestMapper;
import com.api.open.model.ResultModel;
import com.api.open.model.TestModel;
import com.api.open.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 003 on 2018/9/3.
 */
@RestController
public class TestController {

    @Autowired
    TestMapper testMapper;

    @RequestMapping(value = "test")
    public ResultModel getTestContent() {
        List<TestModel> testContent = testMapper.selectTestContent();
        //throw new NoDataException(606,"我错误拉。");
        return ResultUtil.toSuccess(testContent);
    }
}
