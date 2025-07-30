package com.pcshop.orderservice.service;

import com.pcshop.orderservice.dto.PlaceOrderRequest;
import com.pcshop.orderservice.model.Order;
import com.pcshop.orderservice.model.OrderItem;
import com.pcshop.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(String userId, PlaceOrderRequest request) {
        Order order = new Order();
        order.setUserId(userId);

        List<OrderItem> items = request.getItems().stream().map(i -> {
            OrderItem item = new OrderItem();
            item.setProductId(i.getProductId());
            item.setQuantity(i.getQuantity());
            item.setPrice(i.getPrice());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        order.setTotalAmount(items.stream().mapToDouble(i -> i.getQuantity() * i.getPrice()).sum());

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
