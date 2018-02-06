package com.renjunyou.ssmdemo.service;

import java.util.List;

import com.renjunyou.ssmdemo.beans.PageBean;
import com.renjunyou.ssmdemo.beans.User;

public interface UserService {

	List<User> getAllUser();
	
	/**获取用户总数**/
	int getUserCount();
	
	/**
	 * 分页获取用户
	 * @param page  当前页
	 * @return
	 */
	PageBean<User> getUserByPage(int page);

	void saveUser(User user);

	User findById(int id);

	void updateUser(User user);

	void deleteUser(int id);
	
	

}
