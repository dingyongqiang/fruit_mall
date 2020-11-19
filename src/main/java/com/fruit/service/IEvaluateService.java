package com.fruit.service;

import java.util.List;

import com.fruit.model.Evaluate;
import com.github.pagehelper.PageInfo;

public interface IEvaluateService {
	Integer addEvaluate(Evaluate eva,String[] imgs);
	List<Evaluate> findEvaluateByGoodsId(Integer goodsId);
	PageInfo<Evaluate> findAllEvaluate(Integer page,Integer limit,String keyword);
	Integer deleteEvaluate(Integer evaId);
	Evaluate findEvaluteById(Integer evaId);
}
