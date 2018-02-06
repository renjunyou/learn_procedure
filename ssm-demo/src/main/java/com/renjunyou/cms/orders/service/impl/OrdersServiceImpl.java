package com.renjunyou.cms.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renjunyou.cms.orders.beans.Orders;
import com.renjunyou.cms.orders.dao.IOrdersDao;
import com.renjunyou.cms.orders.service.IOrdersService;
import com.renjunyou.ssmdemo.beans.PageBean;

@Service
public class OrdersServiceImpl implements IOrdersService {
	
	@Autowired
	private IOrdersDao orderDao;

	@Override
	public List<Orders> getAllOrders() {
		return orderDao.getAllOrders();
	}

	@Override
	public void addOrders(Orders orders) {
		// TODO Auto-generated method stub

	}

	@Override
	public int updateOrder(Orders orders) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOrderById(int order_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Orders getOrderById(int order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Orders> getOrderByPage(int pageIndex) {
		return orderDao.getOrderByPage(pageIndex);
	}

}
