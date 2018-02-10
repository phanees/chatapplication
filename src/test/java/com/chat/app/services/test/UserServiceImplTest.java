package com.chat.app.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.chat.app.model.Users;
import com.chat.app.repository.UserRepository;
import com.chat.app.service.UserService;
import com.chat.app.service.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	
	@TestConfiguration
	static class UserServiceImplTestContextConfiguration {
		
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
		
		Users user = new Users("phanees", "password", "firstName", "lastName");
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);		
		Mockito.when(userRepository.findByUsername("wronguser")).thenReturn(null);
		Mockito.when(userRepository.findByUsername("")).thenReturn(null);
		
		Users userToBeSaved = new Users("phanees_new","password","firstName","lastName");
		Mockito.when(userRepository.save(userToBeSaved)).thenReturn(userToBeSaved);
	}
	
	@Test
	public void whenValidUsername_thenUserShouldBeFound() {
		String username = "phanees";		
		Users foundUser = userService.findUserByUsername(username);		
		assertThat(foundUser.getUsername()).isEqualTo(username);
	}
	
	@Test
	public void whenInValidUsername_thenUserShouldNotBeFound() {
		String username = "wronguser";		
		Users foundUser = userService.findUserByUsername(username);		
		assertThat(foundUser).isNull();
	}
	
	@Test
	public void whenUsernameIsEmpty_thenUserShouldNotBeFound() {
		String username = "";		
		Users foundUser = userService.findUserByUsername(username);		
		assertThat(foundUser).isNull();
	}
	
	@Test
	public void whenUsernameIsNull_thenUserShouldNotBeFound() {
		String username = null;		
		Users foundUser = userService.findUserByUsername(username);		
		assertThat(foundUser).isNull();
	}
	
	@Test
	public void whenUsernameDoesnotExist_thenUserShouldBeSaved() {
		Users user = new Users("phanees","password","firstname","lastname");	
		UserService us = mock(UserService.class);
		doReturn(user).when(us).saveUser(user);
		Users savedUser = us.saveUser(user);	
		assertThat(savedUser).isNotNull();
		verify(us, times(1)).saveUser(user);
	}
	
	@Test
	public void whenUsernameExists_thenUserNotShouldBeSaved() {
		Users user = new Users("phanees","password","firstname","lastname");	
		Users savedUser = userService.saveUser(user);			
		assertThat(savedUser).isNull();
	}
	
	@Test
	public void whenValidUsername_thenUserShouldBeDeleted() {
		Users user = new Users("phanees_new","password","firstname","lastname");	
		UserService us = mock(UserService.class);
		doNothing().when(us).deleteUser(isA(Users.class));
		us.deleteUser(user);
		assertThat(user).isNotNull();
		verify(us, times(1)).deleteUser(user);
	}
	
	@Test
	public void whenValidUsername_thenUserShouldBeAuthenticated() {
		String username = "phanees";
		String password = "password";
		boolean userAuthenticated = userService.authenticate(username, password);		
		assertThat(userAuthenticated).isEqualTo(true);
	}
	
	@Test
	public void whenInValidUsername_thenUserShouldNotBeAuthenticated() {
		String username = "phanees123";
		String password = "password";
		boolean userAuthenticated = userService.authenticate(username, password);		
		assertThat(userAuthenticated).isEqualTo(false);
	}
	
	@Test
	public void whenEmptyUsernameAndPassword_thenUserShouldNotBeAuthenticated() {
		String username = "";
		String password = "";
		boolean userAuthenticated = userService.authenticate(username, password);		
		assertThat(userAuthenticated).isEqualTo(false);
	}
	
	@Test
	public void whenUsernameIsValid_thenUserShouldBeUpdated() {
		Users user = new Users("phanees","password","firstname","lastname");	
		UserService us = mock(UserService.class);
		doReturn(user).when(us).saveUser(user);
		doReturn(user).when(us).updateUser(user.getUsername(), user);
		Users updatedUser = us.updateUser(user.getUsername(), user);	
		assertThat(updatedUser.getUsername()).isEqualTo(user.getUsername());
		verify(us, times(1)).updateUser(user.getUsername(),user);
	}
}
