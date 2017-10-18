/**
 * 
 */
package com.stashinvest.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.service.IUserService;

/**
 * @author abhimanyu
 *
 */
@RestController
public class UserServiceController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET)
	public List<User> findAllUsers(@RequestParam(required = false) final String query){
		log.info("UserServiceController : findAllUsers -- START");
		List<User> users = new ArrayList<User>();
		try {
			if(StringUtils.isEmpty(query)){
				log.info("UserServiceController : findAllUsers -- getting all users");
				users = userService.findAllUsers();
			}else{
				log.info("UserServiceController : findAllUsers -- query mode: Query Param : " + query);
				users = userService.findAllUsersByParam(query);				
			}
		} catch (UserServiceException e) {
			log.error("UserServiceController: UserServiceController -- ERROR", e);
		}
		log.info("UserServiceController : findAllUsers -- END");
		return users;
	}

}
