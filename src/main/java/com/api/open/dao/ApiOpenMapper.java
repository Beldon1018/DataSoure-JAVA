package com.api.open.dao;

import com.api.open.model.ApiJsonModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by 003 on 2018/9/10.
 */
@Mapper
@Repository
public interface ApiOpenMapper {

    ApiJsonModel getJsonWithUrl(String url);

}
