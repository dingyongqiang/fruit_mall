package com.fruit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fruit.model.Evaluate;
import com.fruit.model.Goods;
import com.fruit.service.IEvaluateService;
import com.fruit.service.IGoodsService;
import com.fruit.service.IRedisEvaluateService;



@Service
public class RedisEvaluateServiceImpl implements IRedisEvaluateService {
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IEvaluateService evaService;


	@Override
	public void RefreshEvaluate(Integer goodsId) {
		System.out.println(goodsId);
		Goods goods = goodsService.findById(goodsId);
		System.out.println(goods.getGoodsName());
		List<Evaluate> evaList = evaService.findEvaluateByGoodsId(goodsId);
		goods.setEvaList(evaList);
	}
}
