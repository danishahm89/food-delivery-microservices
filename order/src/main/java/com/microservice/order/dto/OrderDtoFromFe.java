package com.microservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDtoFromFe {

    private List<FoodItemDto> foodItems;
    private Integer userId;
    private RestaurantDto restaurant;

}
