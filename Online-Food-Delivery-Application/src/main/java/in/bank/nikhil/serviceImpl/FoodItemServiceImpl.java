package in.bank.nikhil.serviceImpl;


import in.bank.nikhil.model.FoodItem;
import in.bank.nikhil.repository.FoodItemRepository;
import in.bank.nikhil.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public void saveFoodItem(FoodItem foodItem) {
        foodItemRepository.save(foodItem);
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found with ID: " + id));
    }

	@Override
	public void deleteFoodItem(Long id) {

		foodItemRepository.deleteById(id);
		
	}


}
