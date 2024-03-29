package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import com.example.demo.login.controller.domain.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	// モックの戻り値設定
	@MockBean
	private UserService service;
	
	@Test
    @WithMockUser
	public void ユーザーリスト画面のユーザー件数のテスト()throws Exception{
		// モックの戻り値設定
		// UserService の count メソッドの戻り値を10に設定
		when(service.count()).thenReturn(10);
		
		//ユーザーリスト画面のチェック
		mockMvc.perform(get("/userList"))
			.andExpect(status().isOk())
            .andExpect(content().string(containsString("合計：10件")));		
	}
	

}
