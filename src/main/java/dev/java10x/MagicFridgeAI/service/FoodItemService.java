package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {
    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> listAll() {
        return foodItemRepository.findAll();
    }

    public FoodItem listById(long id) {
        Optional<FoodItem> foodItemById = foodItemRepository.findById(id);
        return foodItemById.orElse(null);
    }

    public FoodItem update(Long id, FoodItem newFoodItem) {
        Optional<FoodItem> foodItemById = foodItemRepository.findById(id);

        if (foodItemById.isEmpty()) {
            return null;
        }

        newFoodItem.setId(id);
        return foodItemRepository.save(newFoodItem);

    }

    public void deleteById(Long id) {
        foodItemRepository.deleteById(id);
    }


}
