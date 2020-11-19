package com.fruit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fruit.dao.CartMapper;
import com.fruit.model.Cart;
import com.fruit.service.ICartService;



@Service
public class CartServiceImpl implements ICartService {
	@Autowired
	private CartMapper cartMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer addGoodsToCart(Cart cart) {
		Integer rs = cartMapper.insertSelective(cart);
		return rs;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Cart findCartByUserId(Integer userId, Integer goodsId) {
		return cartMapper.findCartByUserId(userId, goodsId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer updateCart(Cart cart) {
		return cartMapper.updateByPrimaryKeySelective(cart);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Cart> findCartByUserId(Integer userId) {
		return cartMapper.findCartListByUserId(userId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteCart(Integer cartId){
		return cartMapper.deleteByPrimaryKey(cartId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Cart findCartById(Integer cartId) {
		return cartMapper.selectByPrimaryKey(cartId);
	}

}
