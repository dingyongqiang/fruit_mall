package com.fruit.service;

import java.util.List;

import com.fruit.model.Goods;
import com.github.pagehelper.PageInfo;


public interface IGoodsService {
	
	List<Goods> findAll();
	
	Goods findById(Integer id);
	Integer update(Goods goods);
	Integer deleteGoods(Integer goodsId);
	PageInfo<Goods> findBySplitPage(Integer page,Integer size,String keyword);

	List<Goods> findGoodsLikeName(String name);
	Integer addGoods(Goods goods);
	List<Goods> findGoodsByVolume(Integer limit);

	Integer changeGoodsState(Integer goodsId,Integer goodsState);

	List<Goods> findAllShow();
}
