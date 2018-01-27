package com.chat.app.controller.test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.chat.app.controller.UserController;
import com.chat.app.model.Users;
import com.chat.app.service.UserService;
import com.chat.app.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testGetUserByUsername() throws Exception {
		Users user = new Users("phanees", "password", "firstName", "lastName");
		given(userService.findUserByUsername("phanees")).willReturn(user);
		
		   mvc.perform(get("http://localhost:8888/api/users/phanees").contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk());
       verify(userService, VerificationModeFactory.times(1)).findUserByUsername("phanees");
       reset(userService);
	}
	
	@Test
	public void testCreateUser() throws Exception {
		Users user = new Users("phanees", "password", "firstName", "lastName");
		
		   mvc.perform(post("http://localhost:8888/api/register").contentType(MediaType.APPLICATION_JSON)
				   .content(JsonUtil.toJson(user)))
		   .andExpect(status().isCreated());
       verify(userService, VerificationModeFactory.times(1)).saveUser(Mockito.anyObject());
       reset(userService);
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		Users user = new Users("phanees", "password", "firstName", "lastName");
	
		   mvc.perform(put("http://localhost:8888/api/update/phanees").contentType(MediaType.APPLICATION_JSON)
				   .content(JsonUtil.toJson(user)))
		   .andExpect(status().isOk());
       verify(userService, VerificationModeFactory.times(1)).updateUser(Mockito.anyString(),Mockito.anyObject());
       reset(userService);
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		   mvc.perform(delete("http://localhost:8888/api/delete/phanees").contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk());
       verify(userService, VerificationModeFactory.times(1)).deleteUser(Mockito.anyObject());
       reset(userService);
	}
	
}
