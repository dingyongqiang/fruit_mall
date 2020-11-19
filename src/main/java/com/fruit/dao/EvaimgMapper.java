package com.fruit.dao;

import java.util.List;

import com.fruit.model.Evaimg;

public interface EvaimgMapper {
    int deleteByPrimaryKey(Integer evaimgId);

    int insert(Evaimg record);

    int insertSelective(Evaimg record);

    Evaimg selectByPrimaryKey(Integer evaimgId);

    int updateByPrimaryKeySelective(Evaimg record);

    int updateByPrimaryKey(Evaimg record);

	List<Evaimg> findEvaimgByEvaId(Integer evaId);

	void addEvaimg(Evaimg evaimg);

	
}