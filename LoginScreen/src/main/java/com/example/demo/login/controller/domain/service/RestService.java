package com.example.demo.login.controller.domain.service;

import java.util.List;

import com.example.demo.login.controller.domain.model.User;

public interface RestService {
	
	//1件登録用のメソッド
	public boolean insert(User user);
	
	//1件検索用メソッド
	public User selectOne(String userId);
	
	//全件検索用メソッド
	public List<User> selectMany();
	
	//1件更新メソッド
	public boolean update(User user);
	
	//1件削除用メソッド
	public boolean delete(String userId);
}
