package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.controller.domain.model.GroupOrder;
import com.example.demo.login.controller.domain.model.SignupForm;
import com.example.demo.login.controller.domain.model.User;
import com.example.demo.login.controller.domain.service.UserService;

@Controller
public class SignupController {

    @Autowired	
    private UserService userService; 
    
	private Map<String,String>radioMarriage;
	//ラジオボタンの初期化メソッド
	private Map<String,String>initRadioMarriage() {
		Map<String,String> radio = new LinkedHashMap<>();
		
		radio.put("既婚","true");
		radio.put("未婚","false");
		
	return radio;	
}
	//@ExceptionHandlerの使い方
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		
		//例外クラスのメッセージをModelに登録
		model.addAttribute("error");
		
		//例外クラスのメッセージをModelに登録
		model.addAttribute("message","SignupController で DataAccessException が発生しました");
		
		//HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
		
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		
		//　例外クラスのメッセージをModelに登録
		model.addAttribute("error","内部サーバーエラー : ExceptionHandler");
		
		//例外クラスのメッセージをModelに登録
		model.addAttribute("message","SignupController で Exception が発生しました");
		
		//HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}

//ポイント１：@ModelAttribute
//ユーザー登録画面のGET用コントローラー	@Validated指定の問題
@GetMapping("/signup")
public String getSignUp(@ModelAttribute SignupForm form,Model model){
	
	//ラジオボタンの初期化メソッド呼び出し
	radioMarriage = initRadioMarriage();
	//ラジオボタン用のMapをModelに登録
	model.addAttribute("radioMarriage",radioMarriage);
	
	return "login/signup";	
}
//ユーザー登録画面のPOST用コントローラー
//ポイント2：データバインド結果の受け取り
@PostMapping("/signup")
public String postSignUp(@ModelAttribute @Validated SignupForm form,BindingResult bindingResult, Model model) {
	
	//ポイント3：データバインド失敗の場合
	//入力チェックに引っかかった場合、ユーザー登録画面に戻る
	if(bindingResult.hasErrors()) {
		
	
	return getSignUp(form,model);
	
	}
	//formの中身をコンソールに出して確認します
	System.out.println(form);
	
	// insert 用の変数
	User user = new User();
	
	user.setUserId(form.getUserId()); // ユーザーID
	user.setPassword(form.getPassword()); // パスワード
	user.setUserName(form.getUserName()); // ユーザー名
	user.setBirthday(form.getBirthday()); // 誕生日
	user.setAge(form.getAge()); // 年齢
	user.setMarriage(form.isMarriage()); // 結婚ステータス
	user.setRole("ROLE_GENERAL"); // ロール (一般)	
	
	// ユーザー登録処理
	boolean result = userService.insert(user);
	
	// ユーザー登録結果
	if(result == true) {
		System.out.println("insert 成功");
	}else {
		System.out.println("insert 失敗");
	}
	
	//login.htmlにリダイレクト
	return "redirect:/login";
  }	
}
