package com.ateaf.ecommerce.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ateaf.ecommerce.order.requests.OrderRequest;
import com.ateaf.ecommerce.order.responses.OrderResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
        @RequestBody @Valid OrderRequest request
    ) {
        log.info("Order Request by customer :: {} ",request.customerId());
        return ResponseEntity.ok(service.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        log.info("fetching All orders");
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findByOrderId(@PathVariable("order-id") Integer orderId){
        log.info("fetching order with OrderId :: {}",orderId);
        return ResponseEntity.ok(service.findByOrderId(orderId));
    }
}
