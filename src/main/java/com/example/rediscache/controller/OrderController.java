package com.example.rediscache.controller;

import com.example.rediscache.domain.Order;
import com.example.rediscache.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getOrder() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        return orderService.updateOrder(order, orderId);
    }

    @PatchMapping("/2/{orderId}")
    public Order updateOrder2(@PathVariable Long orderId, @RequestBody Order order) {
        return orderService.updateOrder(order, orderId);
    }
}
