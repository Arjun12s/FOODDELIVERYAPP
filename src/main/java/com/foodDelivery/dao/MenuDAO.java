package com.foodDelivery.dao;

import java.util.List;
import com.foodDelivery.model.Menu;

public interface MenuDAO {
    Menu getMenuById(int menuId);
    List<Menu> getSimilarMenus(String type, int restaurantId);
	List<Menu> getAllMenuByRestaurantId(int restaurantId);
}
