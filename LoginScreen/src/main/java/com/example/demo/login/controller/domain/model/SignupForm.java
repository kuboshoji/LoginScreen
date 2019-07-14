package com.example.demo.login.controller.domain.model;

import java.sql.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	
	//メールアドレス形式
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")
	private String userId;//ユーザーID
	
	//長さ4から100桁まで、半角英数字のみ
	@NotBlank(message = "{require_check}")
	@Length(min=4,max=100, message = "{length_check}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{pattern_check}")
	private String password;//パスワード
	
	@NotBlank(message = "{require_check}")
	private String userName;//ユーザー名
	
	@NotNull(message = "{require_check}")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday;//誕生日
	
	//値が20から100まで
	@Min(value=20,message = "{min_check}")
	@Max(value=100,message = "{max_check}")
	private int age;//年齢
	
	//falseのみ可能
	@AssertFalse(message = "{false_check}")
	private boolean marriage;//結婚ステータス
	
	}

