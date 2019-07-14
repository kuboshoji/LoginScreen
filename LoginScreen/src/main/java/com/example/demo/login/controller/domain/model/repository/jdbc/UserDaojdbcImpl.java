package com.example.demo.login.controller.domain.model.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.controller.domain.model.User;

@Repository
public class UserDaojdbcImpl implements UserDao{
	
	@Autowired
	JdbcTemplate jdbc;
	
	//Userテーブルの件数を取得
	@Override
	public int count()throws DateAccessException{
		return 0;
	}
	
	//Userテーブルにデータを1件insert
	@Override
	public int insertOne(User user)throws DataAccessException{
		return 0;
	}
	
	//Userテーブルのテーブルを1 件取得
	@Override
	public User selectOne(String userId)throws DataAccessException{
		return unll;
	}

	//Userテーブルの全データを取得
	@Override
	pullic List<User>selectMany()throws DataAccessException{
		return null;
	}
	
	//Userテーブルを1件更新
	@Override
	public int updateOne(User user)throws DataAccessException{
		return 0;
	}
	
	//Userテーブルを1件削除
	@Override
	public int deleteOne(String userId)throws DataAccessException{
		return 0;
	}
	
	//Userテーブルの全データをCSVに出力する
	@Override
	public void userCsvOut()throws DataAccessException{
		
	}
}
