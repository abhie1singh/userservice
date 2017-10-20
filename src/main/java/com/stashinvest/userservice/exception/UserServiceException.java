/**
 * 
 */
package com.stashinvest.userservice.exception;

/**
 * Custom exception class for User Service application.
 * 
 * @author abhimanyu
 *
 */
public class UserServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5638410173250969002L;
	
	public UserServiceException(){
		super();
	}
	
	public UserServiceException(final String message){
		super(message);
	}
	
	public UserServiceException(final Throwable cause){
		super(cause);
	}
	
	public UserServiceException(final String message, final Throwable cause){
		super(message, cause);
	}
	
	

}
