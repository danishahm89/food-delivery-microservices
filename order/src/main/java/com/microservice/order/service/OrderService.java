package com.microservice.order.service;

import com.microservice.order.dto.OrderDto;
import com.microservice.order.dto.OrderDtoFromFe;
import com.microservice.order.dto.UserDto;
import com.microservice.order.entity.Order;
import com.microservice.order.mapper.OrderMapper;
import com.microservice.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private RestTemplate restTemplate;

    public OrderDto createOrder(OrderDtoFromFe orderDetails) {
        Integer newOrderId = sequenceService.getNextOrderId();
        UserDto userDto = fetchUserDetailsByUserId(orderDetails.getUserId());
        Order orderToSave = Order.builder()
                .orderId(newOrderId)
                .foodItems(orderDetails.getFoodItems())
                .restaurant(orderDetails.getRestaurant())
                .user(userDto)
                .build();
        orderRepo.save(orderToSave);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(orderToSave);
    }

    // Resolved through Eureka by service name (RestTemplate is @LoadBalanced)
    private UserDto fetchUserDetailsByUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/users/fetchUserById/" + userId, UserDto.class);
    }

}
