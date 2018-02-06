package com.renjunyou.ssmdemo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renjunyou.common.constants.PageConstants;
import com.renjunyou.ssmdemo.beans.PageBean;
import com.renjunyou.ssmdemo.beans.User;
import com.renjunyou.ssmdemo.dao.UserDao;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Autowired  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }
	

	@Override
	public List<User> getAllUser() {
		List<User> list =  this.getSqlSession().selectList("selectAll");
		return list;
	}

	@Override
	public void saveUser(User user) {
		this.getSqlSession().insert("insertUser", user);
		
	}

	@Override
	public User findById(int id) {
		return this.getSqlSession().selectOne("selectById", id);
	}

	@Override
	public void updateUser(User user) {
		this.getSqlSession().update("updateUser", user);
		
	}

	@Override
	public void deleteUser(int id) {
		this.getSqlSession().delete("deleteById", id);
		
	}

	@Override
	public int getUserCount() {
		return this.getSqlSession().selectOne("selectCount");
	}

	@Override
	public PageBean<User> getUserByPage(int page) {
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		map.put("offset", (page-1)*PageConstants.PAGE_SIZE);
		map.put("rows", PageConstants.PAGE_SIZE);
		//得到总记录数
		int totalCount = this.getSqlSession().selectOne("selectCount");
		//得到分页记录
		List<User> listUser = this.getSqlSession().selectList("selectByPage", map);
		//创建PageBean  返回
		PageBean<User> pb = new PageBean<User>();
		pb.setPc(page);
		pb.setPs(PageConstants.PAGE_SIZE);
		pb.setTr(totalCount);
		pb.setDataList(listUser);
		
		return pb;
	}

}
