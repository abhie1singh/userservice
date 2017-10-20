/**
 * 
 */
package com.stashinvest.userservice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.stashinvest.userservice.constant.UserServiceConstant;

/**
 * This class contains static method to be used by entire application.
 * 
 * @author abhimanyu
 *
 */
public class ServiceUtil {

	/**
	 * This method generate hash key using given hash algorithm for email.
	 * 
	 * @param email
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateHashForKey(final String email) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance(UserServiceConstant.HASH_ALGORITHM);
		messageDigest.update(email.getBytes());
		String encryptedString = new String(messageDigest.digest());
		return encryptedString;
	}

	/**
	 * This method generates hash with salt for password.
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateHashWithSalt(final String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance(UserServiceConstant.HASH_ALGORITHM);
		messageDigest.update((password + UserServiceConstant.SALT).getBytes());
		String encryptedPassword = new String(messageDigest.digest());
		return encryptedPassword;
	}

}
