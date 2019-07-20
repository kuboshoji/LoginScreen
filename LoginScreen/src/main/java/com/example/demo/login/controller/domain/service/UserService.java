package com.example.demo.login.controller.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.controller.domain.model.User;
import com.example.demo.login.controller.domain.model.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	// insert 用のメソッド
	public boolean insert(User user) {
	
	// insert 実行
	int rowNumber = dao.insertOne(user);
	
	//　判定用変数
	boolean result = false;
	
	if(rowNumber > 0) {
		// insert 成功
		result = true;
		
	}
	return result;
		
	}
}
