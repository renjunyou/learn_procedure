package com.renjunyou.ssmdemo.dao;

import java.util.List;

import com.renjunyou.ssmdemo.beans.PageBean;
import com.renjunyou.ssmdemo.beans.User;

public interface UserDao {

	List<User> getAllUser();

	void saveUser(User user);

	User findById(int id);

	void updateUser(User user);

	void deleteUser(int id);

	/**获取用户表数量**/
	int getUserCount();
	
	/**
	 * 分页获取用户
	 * @param offset 起始偏移量，从offset+1 开始
	 * @return
	 */
	PageBean<User> getUserByPage(int offset);
	//SELECT * FROM table LIMIT 5,10;  // 检索记录行 6-15

}
