/**
 * 
 */
package com.stashinvest.userservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * This class is JPA enity object to hold user related data.
 * 
 * @author abhimanyu
 *
 */
@Entity
@Table(name="USER")
@NamedQuery(query = "select u from User u", name = "findAllUsers")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7532873975662892706L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email", length = 200, nullable = false)
	private String email;
	
	@Column(name = "phone_number", length = 20, nullable = false)
	private String phoneNumber;
	
	@Column(name = "full_name", length = 200)
	private String fullName;
	
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "key", length = 100, nullable = false, unique = true)
	private String key;
	
	@Column(name = "account_key", length = 100)
	private String accountKey;
	
	@Column(name = "metadata", length = 2000)
	private String metadata;

	public User() {
		super();
	}
	
	public User(Long id, String email, String phoneNumber, String fullName, String password, String key,
			String accountKey, String metadata) {
		super();
		this.id = id;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.fullName = fullName;
		this.password = password;
		this.key = key;
		this.accountKey = accountKey;
		this.metadata = metadata;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	
	

}
