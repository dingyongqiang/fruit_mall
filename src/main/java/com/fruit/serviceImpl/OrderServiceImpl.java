package com.fruit.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fruit.dao.OrderDetailMapper;
import com.fruit.dao.OrderMapper;
import com.fruit.model.Order;
import com.fruit.model.OrderDetail;
import com.fruit.service.IOrderService;
import com.fruit.utils.OrderSearchVO;
import com.fruit.utils.OrderVO;
import com.fruit.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper detailMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Order takeOrder(Order order) {
		order.setOrderId(UUIDUtil.getUUID());
		Integer rs = orderMapper.insert(order);
		if(rs>0){
			List<OrderDetail> detailList = order.getDetailList();
			for (OrderDetail orderDetail : detailList) {
				orderDetail.setDetailOrder(order);
				detailMapper.insert(orderDetail);
			}
		}
		return order;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Order> findOrdersByUserIdAndState(Integer userId,Integer state){
		List<Order> list = orderMapper.findOrderByUserAndState(state, userId);
		for (Order order : list) {
			List<OrderDetail> detailList = detailMapper.findOrderDetailByOrderId(order.getOrderId());
			order.setDetailList(detailList);
		}
		return list;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer receiveOrder(String orderId) {
		return orderMapper.receiveOrder(orderId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deliverOrder(String orderId, String expressNo) {
		return orderMapper.deliverOrder(orderId, expressNo);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer evaOrder(String orderId) {
		return orderMapper.evaOrder(orderId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteOrder(String orderId) {
		return orderMapper.deleteOrder(orderId);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public PageInfo<Order> findOrdersBySplitPage(Integer page, Integer limit,OrderSearchVO vo) {
		PageHelper.startPage(page, limit);
		List<Order> list =new ArrayList<Order>();
		if(vo!=null){
			list = orderMapper.findAllOrderBySearchVO(vo);
		}else{
			list = orderMapper.findAllOrder();
		}
		PageInfo<Order> info=new PageInfo<Order>(list);
		return info;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer updateOrder(Order order) {
		return orderMapper.updateByPrimaryKey(order);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Order findOrderById(String id) {
		Order order = orderMapper.selectByPrimaryKey(id);
		List<OrderDetail> list = detailMapper.findOrderDetailByOrderId(order.getOrderId());
		order.setDetailList(list);
		return order;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public List<OrderVO> findTotalMoneyByMonth(Integer limit) {
		return orderMapper.findTotalMoneyByMonth(limit);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Integer findTotalOrder() {
		return orderMapper.findTotalOrder();
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Integer findTotalDeliverOrder() {
		return orderMapper.findTotalDeliverOrder();
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer payForOrder(String orderId) {
		return orderMapper.payOrder(orderId);
	}
	@Override
	public Integer updateByPrimaryKeySelective(Order order) {
		return orderMapper.updateByPrimaryKeySelective(order);
	}
	

}
