package com.foodDelivery.model;

import java.sql.Timestamp;

public class User {
	private int userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private String role;
    private Timestamp lastLoginDate;
    private Timestamp createdDate;
	public User() {

	}
	public User(int userId, String username, String password, String email, String address, String phoneNumber,
			String role, Timestamp lastLoginDate, Timestamp createdDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.lastLoginDate = lastLoginDate;
		this.createdDate = createdDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	
	
    
    
    
}
