package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.controller.domain.model.GroupOrder;
import com.example.demo.login.controller.domain.model.SignupForm;

@Controller
public class SignupController {

	private Map<String,String>radioMarriage;
	//ラジオボタンの初期化メソッド
	private Map<String,String>initRadioMarriage() {
		Map<String,String> radio = new LinkedHashMap<>();
		
		radio.put("既婚","true");
		radio.put("未婚","false");
		
	return radio;	
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
	
	//login.htmlにリダイレクト
	return "redirect:/login";
  }	
}
