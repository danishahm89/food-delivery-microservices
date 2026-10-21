package com.service.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDto {

    private int id;
    private String itemName;
    private String description;
    private boolean veg;
    private Number price;
    private Integer restaurantId;
    private Integer quantity;

}
