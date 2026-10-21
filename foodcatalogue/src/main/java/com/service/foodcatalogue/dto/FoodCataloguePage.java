package com.service.foodcatalogue.dto;

import com.service.foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodCataloguePage {

    private List<FoodItem> foodItems;
    private RestaurantDto restaurant;

}
