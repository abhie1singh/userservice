/**
 * 
 */
package com.stashinvest.userservice.constant;

/**
 * This class contains all the constants
 * 
 * @author abhimanyu
 *
 */
public interface UserServiceConstant {
	static String HASH_ALGORITHM = "SHA-256";
	static String SALT= "StashInvestCodingChallengeLongString@#$!%^&*(*)1234567890";
	static String ACCOUNT_SERVICE_URL = "https://account-key-service.herokuapp.com/v1/account";
	static long ACCOUNT_KEY_SERVICE_JOB_FREQUENCY = 50000l; 

}
