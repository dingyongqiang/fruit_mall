package com.fruit.dao;

import java.util.List;

import com.fruit.model.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

	void addOrderDetail(OrderDetail orderDetail);

	List<OrderDetail> findOrderDetailByOrderId(String orderId);
}