package com.chat.app.controller.test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.chat.app.controller.AuthenticationController;
import com.chat.app.service.UserService;
import com.chat.app.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	Map<String, Object> userMap = new HashMap<String, Object>();
	
	@Test
	public void testAuthenticate() throws Exception {
		given(userService.authenticate("phanees","password")).willReturn(true);
		userMap.put("username", "phanees");
		userMap.put("password", "password");
		
		  mvc.perform(post("http://localhost:8888/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				   .content(JsonUtil.toJson(userMap)))
		   .andExpect(status().isOk());
	       verify(userService, VerificationModeFactory.times(1)).authenticate(Mockito.anyString(),Mockito.anyObject());
	       reset(userService);
	}
	
}
