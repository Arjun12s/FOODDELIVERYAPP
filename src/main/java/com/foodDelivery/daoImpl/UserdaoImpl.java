package com.foodDelivery.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.UserDAO;
import com.foodDelivery.model.User;
import com.foodDelivery.util.DBconnection;

public class UserdaoImpl implements UserDAO {

    private static final String INSERT_USER =
        "INSERT INTO user (username, password, email, address, phone_number, role, last_login_date, created_date) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_BY_ID =
        "SELECT * FROM user WHERE user_id = ?";

    private static final String GET_USER_LOGIN =
        "SELECT * FROM user WHERE email = ? AND password = ?";

    private static final String UPDATE_USER =
        "UPDATE user SET username=?, password=?, email=?, address=?, phone_number=?, role=?, last_login_date=? " +
        "WHERE user_id=?";

    private static final String DELETE_USER =
        "DELETE FROM user WHERE user_id=?";

    private static final String GET_ALL_USERS =
        "SELECT * FROM user";

    int rows = 0;

    // ---------------- EMAIL EXISTS ----------------
    @Override
    public boolean emailExists(String email) {
        boolean exists = false;

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("SELECT 1 FROM user WHERE email = ?")) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            exists = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    // ---------------- ADD USER ----------------
    @Override
    public int addUser(User user) {
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_USER)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getRole());
            pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

            rows = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    // ---------------- GET USER BY ID ----------------
    @Override
    public User getUser(int userId) {
        User user = null;

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(GET_USER_BY_ID)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = mapResultSetToUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // ---------------- LOGIN ----------------
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(GET_USER_LOGIN)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = mapResultSetToUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // ---------------- UPDATE USER ----------------
    @Override
    public void updateUser(User user) {

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getRole());
            pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(8, user.getUserId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- DELETE USER ----------------
    @Override
    public void deleteUser(int userId) {

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(DELETE_USER)) {

            pstmt.setInt(1, userId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- GET ALL USERS ----------------
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(GET_ALL_USERS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // ---------------- HELPER METHOD ----------------
    private User mapResultSetToUser(ResultSet rs) throws Exception {

        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setRole(rs.getString("role"));
        user.setCreatedDate(rs.getTimestamp("created_date"));
        user.setLastLoginDate(rs.getTimestamp("last_login_date"));

        return user;
    }
}
