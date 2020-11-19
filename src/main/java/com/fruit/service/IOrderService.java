package com.fruit.service;

import java.util.List;

import com.fruit.model.Order;
import com.fruit.utils.OrderSearchVO;
import com.fruit.utils.OrderVO;
import com.github.pagehelper.PageInfo;


public interface IOrderService {
	Order takeOrder(Order order);
	
	List<Order> findOrdersByUserIdAndState(Integer userId,Integer state);
	
	Integer receiveOrder(String orderId);
	Integer deliverOrder(String orderId,String expressNo);
	Integer evaOrder(String orderId);
	Integer deleteOrder(String orderId);
	PageInfo<Order> findOrdersBySplitPage(Integer page,Integer limit,OrderSearchVO vo);
	Integer updateOrder(Order order);
	Order findOrderById(String id);
	List<OrderVO> findTotalMoneyByMonth(Integer limit);
	Integer findTotalOrder();
	Integer findTotalDeliverOrder();
	Integer payForOrder(String orderId);

	Integer updateByPrimaryKeySelective(Order order);
}
