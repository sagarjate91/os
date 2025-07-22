package com.order.os.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @Hidden
    private Long id;
    private String productName;
    private int quantity;
    private double price;
    private String status;


}
