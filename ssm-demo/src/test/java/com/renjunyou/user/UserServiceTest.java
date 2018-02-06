package com.renjunyou.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.renjunyou.ssmdemo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testGetUserCount() throws Exception {
		//System.out.println(userService.getUserCount());
		
		//System.out.println(userService.getUserByPage(2,3));
		
		
		
		
	}
	
	
}
