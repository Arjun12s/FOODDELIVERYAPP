package com.foodDelivery.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodDelivery.dao.MenuDAO;
import com.foodDelivery.model.Menu;
import com.foodDelivery.util.DBconnection;

public class MenuDaoImpl implements MenuDAO {

    private static final String GET_MENU_BY_RESTAURANT_ID =
        "SELECT m.*, r.name AS restaurant_name " +
        "FROM menu m JOIN restaurant r ON m.restaurant_id = r.restaurant_id " +
        "WHERE m.restaurant_id = ? AND m.is_available = 1";

    private static final String GET_MENU_BY_ID =
        "SELECT m.*, r.name AS restaurant_name " +
        "FROM menu m JOIN restaurant r ON m.restaurant_id = r.restaurant_id " +
        "WHERE m.menu_id = ? AND m.is_available = 1";

    private static final String GET_SIMILAR_MENUS =
        "SELECT m.*, r.name AS restaurant_name " +
        "FROM menu m JOIN restaurant r ON m.restaurant_id = r.restaurant_id " +
        "WHERE m.type = ? AND m.restaurant_id != ? AND m.is_available = 1 " +
        "LIMIT 6";

    @Override
    public List<Menu> getAllMenuByRestaurantId(int restaurantId) {
        List<Menu> list = new ArrayList<>();

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_MENU_BY_RESTAURANT_ID)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Menu getMenuById(int menuId) {
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_MENU_BY_ID)) {

            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return map(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Menu> getSimilarMenus(String type, int currentRestaurantId) {

        List<Menu> list = new ArrayList<>();

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_SIMILAR_MENUS)) {

            ps.setString(1, type);
            ps.setInt(2, currentRestaurantId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ CENTRAL MAPPER
    private Menu map(ResultSet rs) throws Exception {
        return new Menu(
            rs.getInt("menu_id"),
            rs.getInt("restaurant_id"),
            rs.getString("restaurant_name"), // ✅ JOINED FIELD
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getDouble("rating"),
            rs.getString("image_url"),
            rs.getString("type"),
            rs.getBoolean("is_available")
        );
    }
}
