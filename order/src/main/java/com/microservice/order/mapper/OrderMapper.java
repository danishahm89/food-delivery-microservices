package com.microservice.order.mapper;

import com.microservice.order.dto.OrderDto;
import com.microservice.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order mapOrderDtoToOrder(OrderDto orderDto);

    OrderDto mapOrderToOrderDto(Order order);

}
