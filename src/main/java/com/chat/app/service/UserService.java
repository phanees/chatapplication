package com.chat.app.service;

import com.chat.app.model.Users;

/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Find user by username.
	 *
	 * @param username the username
	 * @return the users
	 */
	public Users findUserByUsername(String username);
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return 
	 */
	public Users saveUser(Users user);
	
	/**
	 * Delete user.
	 *
	 * @param user the user
	 */
	public void deleteUser(Users user);
	
	/**
	 * Update user.
	 *
	 * @param username the username
	 * @param user the user
	 */
	public Users updateUser(String username, Users user);
	
	/**
	 * Authenticate user.
	 *
	 * @param username the username
	 * @param password the password
	 * @return boolean - true if success and false if failed
	 */
	public boolean authenticate(String username, String password);
}
