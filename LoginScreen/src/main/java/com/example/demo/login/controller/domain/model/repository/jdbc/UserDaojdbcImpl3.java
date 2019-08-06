package com.example.demo.login.controller.domain.model.repository.jdbc;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.login.controller.domain.model.User;

@Repository("UserDaoJdbcImpl3")
public class UserDaojdbcImpl3 extends UserDaoJdbcImpl{
	
	@Autowired
	private JdbcTemplate jdbc;
	
	//ユーザー 1件取得
	@Override
	public User selectOne(String userId) {
		
		//1件取得用 SQL
		String sql ="SELECT * FROM m_user WHERE user WHERE user_id = ?";
		
		//ポイント：BeanPropertyRowMapper
		//RowMapperの生成
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		
		//SQL実行
		return jdbc.queryForObject(sql, rowMapper, userId);	
	}
	
	//ユーザー全件取得
	@Override
	public List<User> selectMany(){
		
		//M_USER テーブルのテーブルを全件取得するSQL
		String sql = "SELECT * FROM m_user";
		
		//RowMapperの生成
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		
		//SQL実行
		return jdbc.query(sql, rowMapper);
	}

}
