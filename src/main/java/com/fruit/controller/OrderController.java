package com.fruit.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fruit.model.Address;
import com.fruit.model.Cart;
import com.fruit.model.Goods;
import com.fruit.model.Order;
import com.fruit.model.OrderDetail;
import com.fruit.model.Users;
import com.fruit.service.IAddressService;
import com.fruit.service.ICartService;
import com.fruit.service.IGoodsService;
import com.fruit.service.IOrderService;
import com.fruit.utils.AlipayConfig;
import com.fruit.utils.OrderSearchVO;
import com.fruit.utils.OrderVO;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private ICartService cartService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IAddressService addressService;

	
	//下订单
	@RequestMapping("takeOrder")
	public String takeOrder(Integer[] goodslist,Integer addr,Model model,HttpServletRequest request){
		List<Cart> cartList=new ArrayList<Cart>();
		List<OrderDetail> detailList=new ArrayList<OrderDetail>();
		Double totalPrice=0D;
		for (Integer i : goodslist) {
			Cart cart = cartService.findCartById(i);
			totalPrice+=cart.getCartNum()*cart.getCartGoods().getGoodsPrice();
			cartList.add(cart);
			OrderDetail detail=new OrderDetail(cart.getCartGoods(), cart.getCartGoods().getGoodsPrice()*cart.getCartNum(), cart.getCartNum());
			detailList.add(detail);
		}
		HttpSession session = request.getSession();
		Users user=(Users) session.getAttribute("user");
		Date orderDate=new Date();
		Address address = addressService.findAddresById(addr);
		String a=address.getAddrProvince()+address.getAddrCity()+address.getAddrArea()+address.getAddrDetail();
		Order order=new Order(user, orderDate, totalPrice, 1, address.getAddrNickname(), address.getAddrPhone(), a);
		order.setDetailList(detailList);
		Order takeOrder = orderService.takeOrder(order);
		for (Cart c : cartList) {
			Goods goods = goodsService.findById(c.getCartGoods().getGoodsId());
			goods.setGoodsVolume(goods.getGoodsVolume()-c.getCartNum());
			goods.setGoodsVolume(goods.getGoodsVolume()+c.getCartNum());
			goodsService.update(goods);
			cartService.deleteCart(c.getCartId());
		}
		model.addAttribute("order",takeOrder);
		//List<Guess> guessList = guessService.findGuessGoodsByUserId(user.getUserId(), 4);
	//	model.addAttribute("guessList", guessList);
		return "userview/takeorder";
	}
	
	@RequestMapping("findReadPayOrder")
	@ResponseBody
	public List<Order> findReadyPayOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user=(Users) session.getAttribute("user");
		List<Order> orderList = orderService.findOrdersByUserIdAndState(user.getUserId(), 1);
		return orderList;
	}
	@RequestMapping("findReadyToDeliverOrder")
	@ResponseBody
	public List<Order> findReadyToDeliverOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		List<Order> orderList = orderService.findOrdersByUserIdAndState(user.getUserId(), 2);
		return orderList;
	}
	@RequestMapping("findReadyToReceiveOrder")
	@ResponseBody
	public List<Order> findReadToReceiveOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		List<Order> orderList = orderService.findOrdersByUserIdAndState(user.getUserId(), 3);
		return orderList;
	}
	@RequestMapping("findReadyToEvaluateOrder")
	@ResponseBody
	public List<Order> findReadyToEvaluateOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		List<Order> orderList = orderService.findOrdersByUserIdAndState(user.getUserId(), 4);
		return orderList;
	}
	@RequestMapping("findFinishOrder")
	@ResponseBody
	public List<Order> findFinishOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		List<Order> orderList = orderService.findOrdersByUserIdAndState(user.getUserId(), 5);
		return orderList;
		
	}
	
	@RequestMapping("receiveOrder")
	@ResponseBody
	public String receiveOrder(String orderId){
		Integer rs = orderService.receiveOrder(orderId);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping("deleteOrder")
	@ResponseBody
	public String deleteOrder(String orderId){
		Integer rs = orderService.deleteOrder(orderId);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping("findOrderBySplitPage")
	@ResponseBody
	public JSONObject findOrderBySplitPage(Integer page,Integer limit,OrderSearchVO vo){
		if(vo!=null){
			System.out.println(vo.getOrderState()+"========================");
		}
		PageInfo<Order> info = orderService.findOrdersBySplitPage(page, limit,vo);
		JSONObject obj=new JSONObject();
		obj.put("msg", "");
		obj.put("code", 0);
		obj.put("count", info.getTotal());
		obj.put("data", info.getList());
		return obj;
	}
	@RequestMapping("updateOrder")
	@ResponseBody
	public String updateOrder(Order order){
		System.out.println(order.getOrderId());
		System.out.println(order.getOrderUserName()+"sssssssss");
		Integer rs = orderService.updateByPrimaryKeySelective(order);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping("deliverOrder")
	@ResponseBody
	public String deliverOrder(String orderId,String expressNo){
		if(!expressNo.trim().equals("")){
			Integer rs = orderService.deliverOrder(orderId, expressNo);
			if(rs>0){
				return "success";
			}else{
				return "fail";
			}
		}else{
			return "fail";
		}
	}
	@RequestMapping("findOrderById")
	@ResponseBody
	public Order findOrderById(String orderId){
		Order order = orderService.findOrderById(orderId);
		return order;
	}
	@RequestMapping("findTotalOrder")
	@ResponseBody
	public JSONObject findOrderTotalMoney(){
		List<OrderVO> list = orderService.findTotalMoneyByMonth(6);
		String[] month=new String[6];
		Long[] total=new Long[6];
		Integer[] sheets=new Integer[6];
		for(int i=0;i<list.size();i++){
			month[i]=list.get(i).getOrderMonth();
			total[i]=list.get(i).getTotalMoney();
			sheets[i]=list.get(i).getSheets();
		}
		JSONObject obj=new JSONObject();
		obj.put("month", month);
		obj.put("total", total);
		obj.put("sheets", sheets);
		return obj;
	}
	@RequestMapping("findToDo")
	@ResponseBody
	public JSONObject findToDo(){
		Integer totalOrder = orderService.findTotalOrder();
		Integer totalDediver = orderService.findTotalDeliverOrder();
		JSONObject obj=new JSONObject();
		obj.put("total", totalOrder);
		obj.put("deliver", totalDediver);
		return obj;
	}
	
	@RequestMapping("toPay")
	public String toPayFor(String orderId,HttpServletResponse response){
		Integer rs = orderService.payForOrder(orderId);
		if(rs>0){
			return "paysuccess";	
		}else {
			return null;
		}
		
					
	}
	@RequestMapping("notify_url")
	public void notifyUrl(HttpServletRequest request,HttpServletResponse response){
	
		
	}
	@RequestMapping("return_url")
	public String returnUrl(String out_trade_no){
		Integer rs = orderService.payForOrder(out_trade_no);
		if(rs>0){
			System.out.println("同步通知支付成功");
		}
		return "paysuccess";
	}
	
}
