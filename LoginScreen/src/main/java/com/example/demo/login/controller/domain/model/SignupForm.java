package com.example.demo.login.controller.domain.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	
	private String userId;//ユーザーID
	private String password;//パスワード
	private String userName;//ユーザー名
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	private int age;
	private boolean marriage;//結婚ステータス
	
	}

