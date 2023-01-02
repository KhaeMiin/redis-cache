package com.example.rediscache.service;

import com.example.rediscache.domain.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrder(Long orderId);

    Order updateOrder(Order order, Long orderId);

    void deleteOrder(Long orderId);

    List<Order> getAllOrders();

}
