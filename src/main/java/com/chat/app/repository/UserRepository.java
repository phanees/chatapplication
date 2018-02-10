package com.chat.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chat.app.model.Users;


/**
 * The Interface UserRepository.
 */
@Repository("userRepository")
public interface UserRepository extends MongoRepository<Users, String> {
	 
 	/**
 	 * Find by username.
 	 *
 	 * @param username the username
 	 * @return the users
 	 */
 	public Users findByUsername(String username);
 	
 	@SuppressWarnings("unchecked")
	@Override
 	public Users save(Users user);
	 
}
