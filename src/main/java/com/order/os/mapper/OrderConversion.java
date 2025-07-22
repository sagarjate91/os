package com.order.os.mapper;

import com.order.os.dto.OrderDto;
import com.order.os.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderConversion {

    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);


}
