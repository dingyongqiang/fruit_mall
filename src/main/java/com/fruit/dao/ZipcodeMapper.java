package com.fruit.dao;

import com.fruit.model.Zipcode;

public interface ZipcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Zipcode record);

    int insertSelective(Zipcode record);

    Zipcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Zipcode record);

    int updateByPrimaryKey(Zipcode record);
}