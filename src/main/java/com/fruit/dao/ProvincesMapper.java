package com.fruit.dao;

import java.util.List;

import com.fruit.model.Provinces;

public interface ProvincesMapper {
    int deleteByPrimaryKey(String provinceId);

    int insert(Provinces record);

    int insertSelective(Provinces record);

    Provinces selectByPrimaryKey(String provinceId);

    int updateByPrimaryKeySelective(Provinces record);

    int updateByPrimaryKey(Provinces record);

	Provinces findProByProName(String name);

	List<Provinces> findAllProvince();

	Provinces findProByProId(String id);
}