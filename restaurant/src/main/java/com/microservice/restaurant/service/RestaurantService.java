package com.microservice.restaurant.service;

import com.microservice.restaurant.dto.RestaurantDto;
import com.microservice.restaurant.entity.Restaurant;
import com.microservice.restaurant.mapper.RestaurantMapper;
import com.microservice.restaurant.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDto)
                .collect(Collectors.toList());
    }

    public RestaurantDto getRestaurantById(Integer restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant);
    }

    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        Restaurant saved = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(restaurantDto));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(saved);
    }

}
