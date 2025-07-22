package com.order.os.service;


import com.order.os.client.PaymentClient;
import com.order.os.dto.OrderDto;
import com.order.os.dto.OrderRequest;
import com.order.os.dto.OrderResponse;
import com.order.os.dto.PaymentDto;
import com.order.os.entity.Order;
import com.order.os.mapper.OrderConversion;
import com.order.os.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConversion orderConversion;

    @Autowired
    private PaymentClient paymentClient;


    @Override
    @Retryable(
            retryFor = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public OrderResponse createOrder(OrderRequest orderRequest) {

        log.info("Creating order with request: {}", orderRequest);
        //order
        OrderDto orderDto= orderConversion.toDto(orderRepository.save(orderConversion.toEntity(orderRequest.getOrderDto())));
        // payment service call
        PaymentDto paymentDto= paymentClient.createPayment(orderRequest.getPaymentDto()).getBody();
        return new OrderResponse(orderDto,paymentDto);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Optional<Order> order=orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
        return orderConversion.toDto(order.get());
    }




}
