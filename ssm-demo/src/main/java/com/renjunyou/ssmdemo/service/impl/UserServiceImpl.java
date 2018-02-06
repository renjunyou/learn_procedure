package com.renjunyou.ssmdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renjunyou.ssmdemo.beans.PageBean;
import com.renjunyou.ssmdemo.beans.User;
import com.renjunyou.ssmdemo.dao.UserDao;
import com.renjunyou.ssmdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
		
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
		
	}

	@Override
	public PageBean<User> getUserByPage(int page) {
		return userDao.getUserByPage(page);
		
	}

	@Override
	public int getUserCount() {
		return userDao.getUserCount();
	}

}
