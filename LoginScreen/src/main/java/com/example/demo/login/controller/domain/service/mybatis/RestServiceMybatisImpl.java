package com.example.demo.login.controller.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.controller.domain.model.User;
import com.example.demo.login.controller.domain.model.repository.mybatis.UserMapper;
import com.example.demo.login.controller.domain.model.repository.mybatis.UserMapper2;
import com.example.demo.login.controller.domain.service.RestService;

@Transactional
@Service("RestServiceMybatisImpl")
public class RestServiceMybatisImpl implements RestService{
	
	@Autowired
	UserMapper2 userMapper;
	
	@Override
	public boolean insert(User user) {
		//insert 実行
		return userMapper.insert(user);
	}
	
	@Override
	public User selectOne(String userId) {
		//select実行
		return userMapper.selectOne(userId);
	}
	@Override
	public List<User>selectMany(){
		//select実行
		return userMapper.selectMany();
	}
	@Override 
	public boolean update(User user) {
		//update 実行
		return userMapper.updateOne(user);
	}
	@Override
	public boolean delete(String userId) {
		//delete実行
		return userMapper.deleteOne(userId);
	}

}
