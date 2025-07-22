package com.order.os.client;


import com.order.os.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PS",path = "/api/payments")
public interface PaymentClient {

    @PostMapping("/doPayment")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto);
}
