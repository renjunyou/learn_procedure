package com.renjunyou.cms.orders.service;

import java.util.List;

import com.renjunyou.cms.orders.beans.Orders;
import com.renjunyou.ssmdemo.beans.PageBean;

public interface IOrdersService {
	
	/**查询所有订单**/
	List<Orders> getAllOrders();
	/**添加订单**/
	void addOrders(Orders orders);
	/**修改订单**/
	int updateOrder(Orders orders);
	/**根据订单id删除订单**/
	int deleteOrderById(int order_id);
	/**根据订单id查询订单**/
	Orders getOrderById(int order_id);
	/**分页查询订单,传当前页**/
	PageBean<Orders> getOrderByPage(int pageIndex);

}
