package com.chat.app.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.service.UserService;

/**
 * The Class AuthenticationController.
 */
@RestController
public class AuthenticationController {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	/** The user service. */
	@Autowired
	public UserService userService;
	
	/**
	 * Authenticate.
	 *
	 * @param inputObject the input object
	 * @return the response entity
	 */
	@RequestMapping(value = "/api/authenticate", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> authenticate(@RequestBody Map<String, Object> inputObject) {
		logger.info("The username and password received in request is: {}", inputObject);
		
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		String username = inputObject.get("username").toString();
		String password = inputObject.get("password").toString();
		boolean authenticated = userService.authenticate(username, password);
		if(authenticated)
			response = ResponseEntity.status(HttpStatus.OK).build();
		logger.info("Response:::authenticate:::AuthenticationController: {}", response);
		return response;
	}

}
