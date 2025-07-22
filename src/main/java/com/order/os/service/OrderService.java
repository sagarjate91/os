package com.order.os.service;


import com.order.os.dto.OrderDto;
import com.order.os.dto.OrderRequest;
import com.order.os.dto.OrderResponse;

public interface OrderService {

    // Define methods for order management
    OrderResponse createOrder(OrderRequest orderRequest);


    OrderDto getOrderById(Long orderId) ;
}
