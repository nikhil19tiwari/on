package in.bank.vishal.service;

import in.bank.vishal.model.FoodItem;

import java.util.List;

public interface FoodItemService {
    void saveFoodItem(FoodItem foodItem);
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItemById(Long id); // Add this method
    void deleteFoodItem(Long id);
}
