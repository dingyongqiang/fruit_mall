package com.fruit.dao;

import java.util.List;

import com.fruit.model.Areas;

public interface AreasMapper {
    int deleteByPrimaryKey(String areaId);

    int insert(Areas record);

    int insertSelective(Areas record);

    Areas selectByPrimaryKey(String areaId);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);

	Areas findAreaByAreaName(String name, String cityId);

	List<Areas> findAreasByCityId(String cityId);
}