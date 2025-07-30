package com.pcshop.orderservice.controller;

import com.pcshop.orderservice.dto.PlaceOrderRequest;
import com.pcshop.orderservice.model.Order;
import com.pcshop.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderRequest request,
                                        Authentication auth) {
        Order order = service.placeOrder(auth.getName(), request);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(Authentication auth) {
        return ResponseEntity.ok(service.getUserOrders(auth.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id, Authentication auth) {
        Optional<Order> optional = service.getOrderById(id);

        if (optional.isPresent()) {
            Order order = optional.get();
            if (order.getUserId().equals(auth.getName())) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.status(403).body("Forbidden");
            }
        }

        return ResponseEntity.notFound().build();
    }
}
