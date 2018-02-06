package com.renjunyou.cms.orders.beans;

import java.sql.Timestamp;

public class Orders {
	
	private int order_id;//订单id
	private String order_name;//订单名称
	private Timestamp order_time;//下单时间
	private String order_person;//下单人姓名
	
	public Orders() {
		super();
	}
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}

	public String getOrder_person() {
		return order_person;
	}

	public void setOrder_person(String order_person) {
		this.order_person = order_person;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", order_name=" + order_name + ", order_time=" + order_time
				+ ", order_person=" + order_person + "]";
	}
	
	

}
