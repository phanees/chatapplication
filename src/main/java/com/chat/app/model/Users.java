package com.chat.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class Users.
 */
@Document(collection = "chat_users")
public class Users {
	
	/** The id. */
	@Id
	private String id;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/**
	 * Instantiates a new users.
	 */
	public Users() {
		
	}
	
	/**
	 * Instantiates a new users.
	 *
	 * @param username the username
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Users(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param firstName the new firstname
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param lastname the new lastname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append(", password=").append(password)
				.append(", firstName=").append(firstName).append(", lastName=").append(lastName).append("]");
		return builder.toString();
	}

	
}
