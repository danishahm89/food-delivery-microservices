package com.service.foodcatalogue.service;

import com.service.foodcatalogue.dto.FoodCataloguePage;
import com.service.foodcatalogue.dto.FoodItemDto;
import com.service.foodcatalogue.dto.RestaurantDto;
import com.service.foodcatalogue.entity.FoodItem;
import com.service.foodcatalogue.mapper.FoodItemMapper;
import com.service.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
        FoodItem saved = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(saved);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItems = fetchFoodItemList(restaurantId);
        RestaurantDto restaurantDto = fetchRestaurantDetailsById(restaurantId);
        return createFoodCataloguePage(foodItems, restaurantDto);
    }

    public FoodItemDto updateFoodItem(int id, FoodItemDto foodItemDto) {
        FoodItem existing = foodItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found with id: " + id));
        existing.setItemName(foodItemDto.getItemName());
        existing.setDescription(foodItemDto.getDescription());
        existing.setVeg(foodItemDto.isVeg());
        existing.setPrice(foodItemDto.getPrice());
        existing.setRestaurantId(foodItemDto.getRestaurantId());
        existing.setQuantity(foodItemDto.getQuantity());
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemRepo.save(existing));
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItems, RestaurantDto restaurantDto) {
        FoodCataloguePage page = new FoodCataloguePage();
        page.setFoodItems(foodItems);
        page.setRestaurant(restaurantDto);
        return page;
    }

    // Resolved through Eureka by service name (RestTemplate is @LoadBalanced)
    private RestaurantDto fetchRestaurantDetailsById(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurants/fetchById/" + restaurantId, RestaurantDto.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }

}
