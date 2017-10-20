/**
 * 
 */
package com.stashinvest.userservice.model;

import java.io.Serializable;
import java.util.List;

/**
 * Response object for fetch users method
 * 
 * @author abhimanyu
 *
 */
public class UserResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3877087561794157838L;
	private List<User> users;
	
	public UserResponse() {
		super();
	}
	
	public UserResponse(List<User> users) {
		super();
		this.users = users;
	}



	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
