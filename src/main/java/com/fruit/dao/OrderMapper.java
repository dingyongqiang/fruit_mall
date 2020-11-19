package com.fruit.dao;

import java.util.List;

import com.fruit.model.Order;
import com.fruit.utils.OrderSearchVO;
import com.fruit.utils.OrderVO;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	Integer addOrder(Order order);

	List<Order> findOrderByUserAndState(Integer state, Integer userId);

	Integer receiveOrder(String orderId);

	Integer deliverOrder(String orderId, String expressNo);

	Integer deleteOrder(String orderId);

	Integer evaOrder(String orderId);

	List<Order> findAllOrderBySearchVO(OrderSearchVO vo);

	List<Order> findAllOrder();

	Integer updateOrder(Order order);

	Order findOrderById(String id);

	List<OrderVO> findTotalMoneyByMonth(Integer limit);

	Integer findTotalOrder();

	Integer findTotalDeliverOrder();

	Integer payOrder(String orderId);
}