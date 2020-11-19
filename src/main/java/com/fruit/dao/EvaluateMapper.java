package com.fruit.dao;

import java.util.List;

import com.fruit.model.Evaluate;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer evaId);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer evaId);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);

	List<Evaluate> findEvaByGoodsId(Integer goodsId);

	List<Evaluate> findAllEvaluteLikeContent(String keyword);

	List<Evaluate> findAllEvalute();

	Evaluate selectId();
}