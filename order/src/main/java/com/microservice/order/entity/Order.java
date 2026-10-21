package com.microservice.order.entity;

import com.microservice.order.dto.FoodItemDto;
import com.microservice.order.dto.RestaurantDto;
import com.microservice.order.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("orders")
public class Order {

    private Integer orderId;
    private List<FoodItemDto> foodItems;
    private RestaurantDto restaurant;
    private UserDto user;

}
