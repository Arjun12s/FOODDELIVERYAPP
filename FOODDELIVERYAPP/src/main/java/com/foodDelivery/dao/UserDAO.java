package com.foodDelivery.dao;

import java.util.List;

import com.foodDelivery.model.User;

public interface UserDAO {
	int addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
	User getUserByEmailAndPassword(String email, String password);
	boolean emailExists(String email);
}
