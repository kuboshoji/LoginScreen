package com.example.demo.login.controller.domain.model.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.controller.domain.model.User;

@Repository("UserDaoJdbcImpl4")
public class UserDaoJdbcImpl4 extends UserDaoJdbcImpl{
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public List<User> selectMany(){
		
		//M_USER テーブルのデータを全件取得する SQL
		String sql = "SELECT * FROM m_user";
		
		//ResultSetExtractorの生成
		UserResultSetExtractor extractor = new UserResultSetExtractor();
		
		//SQL 実行
		return jdbc.query(sql, extractor);
	}

}
