package com.fruit.dao;

import java.util.List;

import com.fruit.model.Cities;

public interface CitiesMapper {
    int deleteByPrimaryKey(String cityId);

    int insert(Cities record);

    int insertSelective(Cities record);

    Cities selectByPrimaryKey(String cityId);

    int updateByPrimaryKeySelective(Cities record);

    int updateByPrimaryKey(Cities record);

	Cities findCityByCityName(String name, String provinceId);

	List<Cities> findCitiesByProvinceId(String provinceId);
}