/**
 * 
 */
package com.stashinvest.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.model.UserResponse;
import com.stashinvest.userservice.service.IUserService;

/**
 * This class contains rest api methods for User Service.
 * 
 * @author abhimanyu
 *
 */
@Validated
@RestController
public class UserServiceController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	IUserService userService;

	/**
	 * This method fetches users for given query parameter. Fetches all users if
	 * query parameter is null.
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserResponse findAllUsers(@RequestParam(required = false) final String query) throws Exception {
		log.info("UserServiceController : findAllUsers -- START");

		UserResponse userResponse = new UserResponse();
		try {
			List<User> users = new ArrayList<User>();
			// Need to check back on malformed thing

			// if (StringUtils.containsWhitespace(query)) {
			// throw new IllegalArgumentException();
			// }

			if (StringUtils.isEmpty(query)) {
				log.info("UserServiceController : findAllUsers -- getting all users");
				users = userService.findAllUsers();
			} else {
				log.info("UserServiceController : findAllUsers -- query mode: Query Param : " + query);
				users = userService.findAllUsersByParam(query);
			}
			userResponse.setUsers(users);
		} catch (UserServiceException e) {
			log.error("UserServiceController: UserServiceController -- ERROR", e);
			throw new Exception(e.getMessage(), e.getCause());
		}
		log.info("UserServiceController : findAllUsers -- END");
		return userResponse;
	}

	/**
	 * This method validates input parameters and creates user in the database.
	 * 
	 * @param email
	 * @param phoneNumber
	 * @param fullName
	 * @param password
	 * @param metadata
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/users", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(
			@NotEmpty(message = "Email is required") @Email @Size(max = 200, message = "Email is too long") @RequestParam(required = true) final String email,

			@NotEmpty(message = "Phone is required") @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid phone number") @Size(max = 20, min = 1, message = "Phone Number is too long") @RequestParam(required = true) final String phoneNumber,

			@NotEmpty(message = "Full name is required") @Size(max = 200, message = "Full Name is too long") @RequestParam(required = true) final String fullName,

			@NotEmpty(message = "Password is required") @Size(max = 10, message = "Password is too long") @RequestParam(required = true) final String password,

			@NotEmpty(message = "Metadata is required") @Size(max = 2000, message = "Metadata is too long") @RequestParam(required = true) final String metadata)
			throws Exception {

		log.info("UserServiceController : createUser -- START");
		User returnUserValue = null;
		try {
			returnUserValue = userService.createUser(email, phoneNumber, fullName, password, metadata);
		} catch (UserServiceException e) {
			log.error("UserServiceController: createUser -- ERROR", e);
			throw new Exception(e.getMessage(), e.getCause());
		}
		log.info("UserServiceController : createUser -- END");
		return returnUserValue;
	}

}
