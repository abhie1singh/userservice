/**
 * 
 */
package com.stashinvest.userservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.repository.UserRepository;

/**
 * @author abhimanyu
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAllUsers() throws UserServiceException{
		log.info("UserServiceImpl : findAllUsers -- START");
		List<User> users = null;
		try {
			users = userRepository.findAll();
		} catch (Exception e) {
			log.error("UserServiceImpl : findAllUsers -- ERROR");
			throw new UserServiceException(e.getMessage());
		}
		log.info("UserServiceImpl : findAllUsers -- END");
		return users;
	}

	@Override
	public List<User> findAllUsersByParam(final String query) throws UserServiceException {
		log.info("UserServiceImpl : findAllUsersByMetadata -- START");
		List<User> users = null;
		try {
			users = userRepository.findByMetadata(query);
		} catch (Exception e) {
			log.error("UserServiceImpl : findAllUsers -- ERROR");
			throw new UserServiceException(e.getMessage());
		}
		log.info("UserServiceImpl : findAllUsersByMetadata -- END");
		return users;
	}
}
