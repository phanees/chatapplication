package com.chat.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.app.model.Users;
import com.chat.app.repository.UserRepository;


/**
 * The Class UserServiceImpl.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/**
    * Find user by username.
    *
    * @param username the username
    * @return the users
    */
   @Override
	public Users findUserByUsername(String username) {
	   logger.info("Inside UserServiceImpl --> findUserByUsername():::::: {}", username); 
	   Users user = userRepository.findByUsername(username);
	   logger.info("Inside UserServiceImpl --> findUserByUsername():::::: User being returned {}", user);
		return user;
	}

	/**
	 * Save user.
	 *
	 * @param user the user
	 */
	@Override
	public Users saveUser(Users user) {
		logger.info("Inside UserServiceImpl --> saveUser():::::: {}", user); 
		user.setPassword(user.getPassword()); 
       	return userRepository.save(user);
	}

	/**
	 * Delete user.
	 *
	 * @param user the user
	 */
	@Override
	public void deleteUser(Users user) {
		logger.info("Inside UserServiceImpl --> deleteUser():::::: {}", user); 
		userRepository.delete(user);
		
	}

	/**
	 * Update user.
	 *
	 * @param username the username
	 * @param user the user
	 */
	@Override
	public Users updateUser(String username, Users user) {
		logger.info("Inside UserServiceImpl --> UpdateUser():::::: username: {}, user: {}", username, user); 
		Users existingUser = findUserByUsername(username);
		userRepository.delete(existingUser);
		return saveUser(user);
	}
	
	/**
	 * Authenticate user.
	 *
	 * @param username the username
	 * @param password the password
	 * @return boolean - true if success and false if failed
	 */
	@Override
	public boolean authenticate(String username, String password) {
		logger.info("Inside UserServiceImpl --> authenticate():::::: username: {}, password: {}", username, password); 
		boolean response = false;
		if(username != null && password != null) {
			Users user = findUserByUsername(username);
			if(user != null && user.getPassword().equals(password)) {
				logger.info("Inside UserServiceImpl --> authenticate()::::::user authenticated successfully");
				response =  true;
			}
		}
		return response;
	}

}
