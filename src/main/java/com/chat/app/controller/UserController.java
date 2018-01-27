package com.chat.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.model.Users;
import com.chat.app.service.UserService;

/**
 * The Class UserController.
 */
@RestController
public class UserController {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/** The user service. */
	@Autowired
	public UserService userService;
	
	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 */
	@RequestMapping(value = "/api/users/{username:.+}", method=RequestMethod.GET)
	public Users getUserByUsername(@PathVariable("username") String username) {
		logger.info("Inside UserController --> getUserByUsername :: {} ", username);
		return userService.findUserByUsername(username);
	}
	
	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the response entity
	 */
	@RequestMapping(value = "/api/register", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createUser(@RequestBody Users user) {
		logger.info("Inside UserController --> createUser :: ::: {} ", user);
		userService.saveUser(user);
		return	ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * Update user.
	 *
	 * @param username the username
	 * @param user the user
	 * @return the response entity
	 */
	@RequestMapping(value = "/api/update/{username:.+}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<String> updateUser(@PathVariable("username") String username, @RequestBody Users user) {
		logger.info("Inside UserController --> updateUser :: {} ::: user ::: {}", username, user);
		userService.updateUser(username, user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	/**
	 * Delete user.
	 *
	 * @param username the username
	 * @return the response entity
	 */
	@RequestMapping(value = "/api/delete/{username:.+}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
		logger.info("Inside UserController --> deleteUser :: {}", username);
		Users user = userService.findUserByUsername(username);
		userService.deleteUser(user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
