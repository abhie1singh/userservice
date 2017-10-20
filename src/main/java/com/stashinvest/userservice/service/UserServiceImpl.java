/**
 * 
 */
package com.stashinvest.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.repository.UserRepository;
import com.stashinvest.userservice.util.ServiceUtil;

/**
 * Implementation class for User service interface.
 * 
 * @author abhimanyu
 *
 */
/**
 * @author abhimanyu
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.stashinvest.userservice.service.IUserService#findAllUsers()
	 */
	@Override
	public List<User> findAllUsers() throws UserServiceException {
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

	/* (non-Javadoc)
	 * @see com.stashinvest.userservice.service.IUserService#findAllUsersByParam(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.stashinvest.userservice.service.IUserService#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	public User createUser(final String email, final String phoneNumber, final String fullName, final String password,
			final String metadata) throws UserServiceException {
		log.info("UserServiceImpl : createUser -- START");
		User returnValueUser = null;
		try {
			User user = new User();
			user.setEmail(email);
			user.setPhoneNumber(phoneNumber);
			user.setFullName(fullName);
			user.setPassword(ServiceUtil.generateHashWithSalt(password));
			user.setMetadata(metadata);
			user.setKey(ServiceUtil.generateHashForKey(email));

			returnValueUser = userRepository.save(user);
		} catch (Exception e) {
			log.error("UserServiceImpl : createUser -- ERROR");
			throw new UserServiceException(e.getMessage());
		}
		log.info("UserServiceImpl : createUser -- END");
		return returnValueUser;
	}

	/* (non-Javadoc)
	 * @see com.stashinvest.userservice.service.IUserService#findByEmptyAccountKey()
	 */
	@Override
	public List<User> findByEmptyAccountKey() throws UserServiceException {
		return userRepository.findByEmptyKey();
	}

	/* (non-Javadoc)
	 * @see com.stashinvest.userservice.service.IUserService#updateAccountKey(java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	public void updateAccountKey(final String email, final String accountKey) throws UserServiceException {
		List<User> users = this.findAllUsersByParam(email);
		if(!CollectionUtils.isEmpty(users)){
			User user = users.get(0);
			user.setAccountKey(accountKey);
		}
	}
}
