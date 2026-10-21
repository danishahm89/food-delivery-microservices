package com.service.foodcatalogue.controller;

import com.service.foodcatalogue.dto.FoodCataloguePage;
import com.service.foodcatalogue.dto.FoodItemDto;
import com.service.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    @Autowired
    private FoodCatalogueService foodCatalogueService;

    /** Adds a food item to the catalogue. */
    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody FoodItemDto foodItemDto) {
        return new ResponseEntity<>(foodCatalogueService.addFoodItem(foodItemDto), HttpStatus.CREATED);
    }

    /** Fetches a restaurant's details together with its food items. */
    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchFoodItemById(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId));
    }

    /** Updates an existing food item. */
    @PutMapping("/updateFoodItem/{id}")
    public ResponseEntity<FoodItemDto> updateFoodItem(@PathVariable int id, @RequestBody FoodItemDto foodItemDto) {
        return new ResponseEntity<>(foodCatalogueService.updateFoodItem(id, foodItemDto), HttpStatus.OK);
    }

}
