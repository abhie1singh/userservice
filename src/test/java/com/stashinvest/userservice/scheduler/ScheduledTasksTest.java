/**
 * 
 */
package com.stashinvest.userservice.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.stashinvest.userservice.constant.UserServiceConstant;
import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.AccountServiceVO;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.service.IUserService;

/**
 * @author abhimanyu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ScheduledTasksTest {
	@Mock
	private IUserService userService;
	
	@InjectMocks
	private ScheduledTasks scheduledTasks;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.stashinvest.userservice.scheduler.ScheduledTasks#updateAccountKey()}.
	 */
	@Test
	public void testUpdateAccountKey() throws Exception {
		
		AccountServiceVO vo = new AccountServiceVO();
		vo.setEmail("test@test.com");
		vo.setAccountKey("accountKey");
		
		User user = new User();
		user.setEmail("test@test.com");
		
		List<User> users = new ArrayList<User>();
		users.add(user);
		
		RestTemplate restTemplate = new RestTemplate();
		AccountServiceVO response = restTemplate.postForObject(UserServiceConstant.ACCOUNT_SERVICE_URL, vo, AccountServiceVO.class);
		
		Mockito.when(userService.findByEmptyAccountKey()).thenReturn(users);
		Mockito.doNothing().when(userService).updateAccountKey(response.getEmail(), response.getAccountKey());
		scheduledTasks.updateAccountKey();
		Mockito.verify(userService, Mockito.atLeastOnce()).findByEmptyAccountKey();
		Mockito.verify(userService, Mockito.atLeastOnce()).updateAccountKey(response.getEmail(), response.getAccountKey());
	}
	
	@Test(expected = UserServiceException.class)
	public void testUpdateAccountKey_exception() throws Exception {
		
		AccountServiceVO vo = new AccountServiceVO();
		vo.setEmail("test@test.com");
		vo.setAccountKey("accountKey");
		
		User user = new User();
		user.setEmail("test@test.com");
		
		List<User> users = new ArrayList<User>();
		users.add(user);
		
		RestTemplate restTemplate = new RestTemplate();
		AccountServiceVO response = restTemplate.postForObject(UserServiceConstant.ACCOUNT_SERVICE_URL, vo, AccountServiceVO.class);
		
		Mockito.when(userService.findByEmptyAccountKey()).thenThrow(Mockito.mock(UserServiceException.class));
		Mockito.doNothing().when(userService).updateAccountKey(response.getEmail(), response.getAccountKey());
		scheduledTasks.updateAccountKey();
		Mockito.verify(userService, Mockito.atLeastOnce()).findByEmptyAccountKey();
		Mockito.verify(userService, Mockito.atLeastOnce()).updateAccountKey(response.getEmail(), response.getAccountKey());
	}

}
