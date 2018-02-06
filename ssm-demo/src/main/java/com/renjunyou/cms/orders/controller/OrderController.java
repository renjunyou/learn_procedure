package com.renjunyou.cms.orders.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renjunyou.cms.orders.beans.Orders;
import com.renjunyou.cms.orders.service.IOrdersService;
import com.renjunyou.common.BaseController;
import com.renjunyou.ssmdemo.beans.PageBean;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {
	@Autowired
	private IOrdersService orderService;

	@RequestMapping(value = "/orderList")
	public String getAllOrders(Model model) {

		List<Orders> orderList = orderService.getAllOrders();
		model.addAttribute("orderList", orderList);

		return "orders/orderList";
	}

	@RequestMapping(value = "/orderListByPage")
	public String getPageOrders(String page, Model model, HttpServletRequest req) {

		// 请求当前页数据
		int pageIndex = -999;

		// 1.判断请求page参数 以获取当前页码
		if (page == null) {
			pageIndex = 1;
		} else {
			pageIndex = Integer.parseInt(page);
		}

		// 2.使用UserService查询，得到PageBean
		PageBean<Orders> pb = orderService.getOrderByPage(pageIndex);

		// 3. 获取url，设置给PageBean
		String url = getUrl(req);
		pb.setUrl(url);

		model.addAttribute("pb", pb);


		return "orders/orderList";
	}

}
