/**
 * 
 */
package com.stashinvest.userservice.service;

import java.util.List;

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;

/**
 * Service interface
 * 
 * @author abhimanyu
 *
 */
public interface IUserService {
	/**
	 * This method fetches all users from database
	 * 
	 * @return
	 * @throws UserServiceException
	 */
	List<User> findAllUsers() throws UserServiceException;
	/**
	 * This method fetches users based on parameter. Parameter could be email, full name or metadata.
	 * 
	 * @param query
	 * @return
	 * @throws UserServiceException
	 */
	List<User> findAllUsersByParam(final String query) throws UserServiceException;
	/**
	 * This method create user in database using input parameters.
	 * 
	 * @param email
	 * @param phoneNumber
	 * @param fullName
	 * @param password
	 * @param metadata
	 * @return
	 * @throws UserServiceException
	 */
	User createUser(final String email, final String phoneNumber, final String fullName, final String password, final String metadata) throws UserServiceException;
	/**
	 * This method finds all users with empty or null account key.
	 * 
	 * @return
	 * @throws UserServiceException
	 */
	List<User> findByEmptyAccountKey() throws UserServiceException;
	/**
	 * This method updates account key for given email id.
	 * 
	 * @param email
	 * @param accountKey
	 * @throws UserServiceException
	 */
	void updateAccountKey(final String email, final String accountKey) throws UserServiceException;
}
