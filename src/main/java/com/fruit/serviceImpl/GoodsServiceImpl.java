package com.fruit.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.fruit.dao.GoodsMapper;
import com.fruit.model.Evaluate;
import com.fruit.model.Goods;
import com.fruit.service.IEvaluateService;
import com.fruit.service.IGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class GoodsServiceImpl implements IGoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private IEvaluateService evaluateService;
	@Override
	public List<Goods> findAll() {
		 List<Goods>  list=goodsMapper.findAll();
		return list;
	}
	@Override
	public Goods findById(Integer id) {
		Goods good=goodsMapper.selectByPrimaryKey(id);
		return good;
	}
	@Override
	public Integer update(Goods goods) {
		int i=goodsMapper.updateByPrimaryKeySelective(goods);
		return i;
	}
	@Override
	public Integer deleteGoods(Integer goodsId) {
		int i=goodsMapper.deleteByPrimaryKey(goodsId);
		return i;
	}
	/*
	 * 分页(non-Javadoc)
	 * @see com.fruit.service.IGoodsService#findBySplitPage(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public PageInfo<Goods> findBySplitPage(Integer page, Integer size, String keyword) {
		List<Goods> list =new ArrayList<Goods>();
		PageHelper.startPage(page, size);
		if(keyword!=null &&!keyword.trim().equals("")){
			list=goodsMapper.findGoodsLikeName(keyword);
		}else{
			list = goodsMapper.findAll();
		}
		PageInfo<Goods> info=new PageInfo<Goods>(list);
		return info;
	}
	
	
	@Override
	public List<Goods> findGoodsLikeName(String name) {
		List<Goods> list=goodsMapper.findGoodsLikeName(name);
		return list;
	}
	@Override
	public Integer addGoods(Goods goods) {
		int i=goodsMapper.insert(goods);
		return i;
	}
	@Override
	public List<Goods> findGoodsByVolume(Integer limit) {
		 List<Goods> list=goodsMapper.findGoodsByVolume(limit);
		return null;
	}
	@Override
	public Integer changeGoodsState( Integer goodsId,Integer goodsState) {
		int i=goodsMapper.changeGoodsState( goodsId,goodsState);
		return i;
	}
	@Override
	public List<Goods> findAllShow() {
		List<Goods> list=goodsMapper.findAllShow();
		return list;
	}
	
	
	
}
