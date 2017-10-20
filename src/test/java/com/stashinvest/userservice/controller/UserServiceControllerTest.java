package com.stashinvest.userservice.controller;

import static org.junit.Assert.assertEquals;

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

import com.stashinvest.userservice.exception.UserServiceException;
import com.stashinvest.userservice.model.User;
import com.stashinvest.userservice.model.UserResponse;
import com.stashinvest.userservice.service.IUserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceControllerTest {
	@Mock
	private IUserService userService;
	@InjectMocks
	private UserServiceController userServiceController;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAllUsers_query_empty() throws Exception {
		User user = new User();
		user.setEmail("test@test.com");
		List<User> users = new ArrayList<User>();
		users.add(user);
		UserResponse expectedResponse = new UserResponse();
		expectedResponse.setUsers(users);
		
		Mockito.when(userService.findAllUsers()).thenReturn(users);
		UserResponse actualRespone = userServiceController.findAllUsers("");
		Mockito.verify(userService, Mockito.atLeastOnce()).findAllUsers();
		assertEquals(expectedResponse.getUsers().get(0).getEmail(), actualRespone.getUsers().get(0).getEmail());
		
	}
	
	@Test
	public void testFindAllUsers_query_null() throws Exception {
		User user = new User();
		user.setEmail("test@test.com");
		List<User> users = new ArrayList<User>();
		users.add(user);
		UserResponse expectedResponse = new UserResponse();
		expectedResponse.setUsers(users);
		
		Mockito.when(userService.findAllUsers()).thenReturn(users);
		UserResponse actualRespone = userServiceController.findAllUsers(null);
		Mockito.verify(userService, Mockito.atLeastOnce()).findAllUsers();
		assertEquals(expectedResponse.getUsers().get(0).getEmail(), actualRespone.getUsers().get(0).getEmail());
		
	}
	
	@Test
	public void testFindAllUsers_query_notempty() throws Exception {
		User user = new User();
		user.setEmail("test@test.com");
		List<User> users = new ArrayList<User>();
		users.add(user);
		UserResponse expectedResponse = new UserResponse();
		expectedResponse.setUsers(users);
		
		Mockito.when(userService.findAllUsersByParam("test@test.com")).thenReturn(users);
		UserResponse actualRespone = userServiceController.findAllUsers("test@test.com");
		Mockito.verify(userService, Mockito.atLeastOnce()).findAllUsersByParam("test@test.com");
		assertEquals(expectedResponse.getUsers().get(0).getEmail(), actualRespone.getUsers().get(0).getEmail());
		
	}
	
	@Test(expected = Exception.class)
	public void testFindAllUsers_query_exception() throws Exception {
		Mockito.when(userService.findAllUsersByParam("test@test.com")).thenThrow(Mockito.mock(UserServiceException.class));
		userServiceController.findAllUsers("test@test.com");
		Mockito.verify(userService, Mockito.atLeastOnce()).findAllUsersByParam("test@test.com");		
	}
	
	

	@Test
	public void testCreateUser() throws Exception {
		final User expected = new User();
		expected.setEmail("test@test.com");
		
		final String email = "test@test.com";
		final String phoneNumber = "1212121212";
		final String fullName = "Joe Smith";
		final String password = "password";
		final String metadata = "metadata";
		
		Mockito.when(userService.createUser(email, phoneNumber, fullName, password, metadata)).thenReturn(expected);
		User actual = userServiceController.createUser(email, phoneNumber, fullName, password, metadata);
		Mockito.verify(userService, Mockito.atLeastOnce()).createUser(email, phoneNumber, fullName, password, metadata);
		assertEquals(expected.getEmail(), actual.getEmail());
	}
	
	@Test(expected = Exception.class)
	public void testCreateUser_exception() throws Exception {
		
		final String email = "test";
		final String phoneNumber = "1212121212";
		final String fullName = "Joe Smith";
		final String password = "password";
		final String metadata = "metadata";
		
		Mockito.when(userService.createUser(email, phoneNumber, fullName, password, metadata)).thenThrow(Mockito.mock(UserServiceException.class));
		userServiceController.createUser(email, phoneNumber, fullName, password, metadata);
		Mockito.verify(userService, Mockito.atLeastOnce()).createUser(email, phoneNumber, fullName, password, metadata);
	}

}
