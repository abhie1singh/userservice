/**
 * 
 */
package com.stashinvest.userservice.service;

import java.util.List;

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;

/**
 * @author abhimanyu
 *
 */
public interface IUserService {
	List<User> findAllUsers() throws UserServiceException;
	List<User> findAllUsersByParam(final String query) throws UserServiceException;
}
