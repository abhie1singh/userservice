/**
 * 
 */
package com.stashinvest.userservice.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.repository.UserRepository;
import com.stashinvest.userservice.util.ServiceUtil;

/**
 * @author abhimanyu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	User user = null;
	List<User> users = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setEmail("test@test.com");
		users = new ArrayList<User>();
		users.add(user);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test method for
	 * {@link com.stashinvest.userservice.service.UserServiceImpl#findAllUsers()}.
	 */
	@Test
	public void testFindAllUsers() throws Exception {
		Mockito.when(userRepository.findAll()).thenReturn(users);
		List<User> actual = userServiceImpl.findAllUsers();
		Mockito.verify(userRepository, Mockito.atLeastOnce()).findAll();
		assertEquals("test@test.com", actual.get(0).getEmail());
	}

	@Test(expected = Exception.class)
	public void testFindAllUsers_exception() throws Exception {
		Mockito.when(userRepository.findAll()).thenThrow(Mockito.mock(SQLGrammarException.class));
		userServiceImpl.findAllUsers();
		Mockito.verify(userRepository, Mockito.atLeastOnce()).findAll();
	}

	/**
	 * Test method for
	 * {@link com.stashinvest.userservice.service.UserServiceImpl#findAllUsersByParam(java.lang.String)}.
	 */
	@Test
	public void testFindAllUsersByParam() throws Exception {
		Mockito.when(userRepository.findByMetadata("test@test.com")).thenReturn(users);
		List<User> actual = userServiceImpl.findAllUsersByParam("test@test.com");
		Mockito.verify(userRepository, Mockito.atLeastOnce()).findByMetadata("test@test.com");
		assertEquals("test@test.com", actual.get(0).getEmail());
	}

	@Test(expected = Exception.class)
	public void testFindAllUsersByParam_exception() throws Exception {
		Mockito.when(userRepository.findByMetadata("test@test.com")).thenThrow(Mockito.mock(Exception.class));
		userServiceImpl.findAllUsersByParam("test@test.com");
		Mockito.verify(userRepository, Mockito.atLeastOnce()).findByMetadata("test@test.com");
	}

	/**
	 * Test method for
	 * {@link com.stashinvest.userservice.service.UserServiceImpl#findByEmptyAccountKey()}.
	 */
	@Test
	public void testFindByEmptyAccountKey() throws Exception {
		Mockito.when(userRepository.findByEmptyKey()).thenReturn(users);
		List<User> actual = userServiceImpl.findByEmptyAccountKey();
		Mockito.verify(userRepository, Mockito.atLeastOnce()).findByEmptyKey();
		assertEquals("test@test.com", actual.get(0).getEmail());
	}

}
