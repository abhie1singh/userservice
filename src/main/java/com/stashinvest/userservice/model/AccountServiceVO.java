/**
 * 
 */
package com.stashinvest.userservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object to create request and handle response object of Account service.
 * 
 * @author abhimanyu
 *
 */
public class AccountServiceVO {
	@JsonProperty(value = "email")
	private String email;
	@JsonProperty(value = "account_key")
	private String accountKey;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	

}
