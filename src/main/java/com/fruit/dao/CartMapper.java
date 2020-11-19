package com.fruit.dao;

import java.util.List;

import com.fruit.model.Cart;

public interface CartMapper {
    int deleteByPrimaryKey(Integer cartId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

	Cart findCartByUserId(Integer userId, Integer goodsId);

	List<Cart> findCartListByUserId(Integer userId);
}