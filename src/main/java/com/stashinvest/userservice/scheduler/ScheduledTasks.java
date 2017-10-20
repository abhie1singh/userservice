/**
 * 
 */
package com.stashinvest.userservice.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.stashinvest.userservice.constant.UserServiceConstant;
import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.AccountServiceVO;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.service.IUserService;

/**
 * This class has scheduled task methods.
 * 
 * @author abhimanyu
 *
 */
@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	IUserService userService;

	/**
	 * This method fetches users with empty key. For each user, calls account key service and updates key for the user.
	 * 
	 * @throws UserServiceException
	 */
	@Scheduled(fixedRate = UserServiceConstant.ACCOUNT_KEY_SERVICE_JOB_FREQUENCY)
	public void updateAccountKey() throws UserServiceException {
		log.info("ScheduledTasks : updateAccountKey -- START");
		try {
			List<User> users = userService.findByEmptyAccountKey();
			if(!CollectionUtils.isEmpty(users)){
				for (User user : users) {
					AccountServiceVO input = new AccountServiceVO();
					input.setAccountKey(user.getKey());
					input.setEmail(user.getEmail());
					AccountServiceVO response = this.getAccountKeyFromService(input);
					if(!ObjectUtils.isEmpty(response)){
						this.updateAccountKey(response.getEmail(), response.getAccountKey());
					}else{
						throw new UserServiceException("Account Service is unavailable");
					}
				}
			}
		} catch (Exception e) {
			log.error("ScheduledTasks : updateAccountKey -- ERROR");
			throw new UserServiceException(e.getMessage(), e.getCause());
		}
		log.info("ScheduledTasks : updateAccountKey -- END");	
	}
	
	/**
	 * This method calls remote account key service and returns response.
	 * 
	 * @param input
	 * @return
	 * @throws UserServiceException
	 */
	private AccountServiceVO getAccountKeyFromService(final AccountServiceVO input) throws UserServiceException{
		log.info("ScheduledTasks : getAccountKeyFromService -- START");
		AccountServiceVO response;
		try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(UserServiceConstant.ACCOUNT_SERVICE_URL, input, AccountServiceVO.class);
		} catch (RestClientException e) {
			log.error("ScheduledTasks : getAccountKeyFromService -- ERROR");
			throw new UserServiceException(e.getMessage(), e.getCause());
		}catch (Exception e) {
			log.error("ScheduledTasks : getAccountKeyFromService -- ERROR");
			throw new UserServiceException(e.getMessage(), e.getCause());
		}
		log.info("ScheduledTasks : getAccountKeyFromService -- END");
		return response;
	}
	
	/**
	 * This method updates account key for given email id.
	 * 
	 * @param email
	 * @param accountKey
	 * @throws UserServiceException
	 */
	private void updateAccountKey(final String email, final String accountKey) throws UserServiceException{
		log.info("ScheduledTasks : updateAccountKey -- START");
		try {
			userService.updateAccountKey(email, accountKey);
		} catch (Exception e) {
			log.error("ScheduledTasks : updateAccountKey -- ERROR");
			throw new UserServiceException(e.getMessage(), e.getCause());
		}
		log.info("ScheduledTasks : updateAccountKey -- END");
	}

}
