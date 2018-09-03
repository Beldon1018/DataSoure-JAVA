package com.api.open.dao;

import com.api.open.model.TestModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 003 on 2018/9/3.
 */
@Mapper
@Repository
public interface TestMapper {
    List<TestModel> selectTestContent();
}
