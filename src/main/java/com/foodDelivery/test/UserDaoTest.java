package com.foodDelivery.test;

import java.util.List;

import com.foodDelivery.dao.UserDAO;
import com.foodDelivery.daoImpl.UserdaoImpl;
import com.foodDelivery.model.User;

public class UserDaoTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserdaoImpl();

        // ---------- ADD USER ----------
//	        User newUser = new User();
//	        newUser.setUsername("Arjun");
//	        newUser.setPassword("1234");
//	        newUser.setEmail("arjun@test.com");
//	        newUser.setAddress("Bangalore");
//	        newUser.setPhoneNumber("9999999999");
//	        newUser.setRole("USER");

//	        int result = userDAO.addUser(newUser);
//	        System.out.println("User Inserted: " + result);

        // ---------- EMAIL EXISTS ----------
        System.out.println("Email exists: " +
                userDAO.emailExists("arjun@test.com"));

        // ---------- GET USER BY ID ----------
        User user = userDAO.getUser(1);
        if (user != null) {
            System.out.println("Fetched User: " + user.getUsername());
        }

        // ---------- LOGIN ----------
        User loggedUser =
                userDAO.getUserByEmailAndPassword("arjun@test.com", "1234");

        if (loggedUser != null) {
            System.out.println("Login Success: " + loggedUser.getUsername());
        }

        // ---------- UPDATE USER ----------
        if (user != null) {
            user.setAddress("Hyderabad");
            userDAO.updateUser(user);
            System.out.println("User Updated");
        }

        // ---------- GET ALL USERS ----------
        List<User> users = userDAO.getAllUsers();
        System.out.println("Total Users: " + users.size());

        for (User u : users) {
            System.out.println(u.getUserId() + " - " + u.getUsername());
        }

        // ---------- DELETE USER ----------
        userDAO.deleteUser(16);
        System.out.println("User Deleted");
    }
}
