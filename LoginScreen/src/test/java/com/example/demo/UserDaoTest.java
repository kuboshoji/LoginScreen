package com.example.demo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.login.controller.domain.model.repository.UserDao;

//テスト用アノテーション
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {
	
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;
	
	//カウントメソッドのテスト1
	@Test
	public void countTest1() {
		
		//カウントメソッドの結果が2件であることをテスト
		assertEquals(dao.count(),2);
	}
	//カウントメソッドのテスト2
	@Test
	@Sql("/testdata.spl")
	public void countTest2() {
		
		//カウントメソッドの結果が3件であることをテスト
		assertEquals(dao.count(),3);
	}
}


