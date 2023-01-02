package com.example.rediscache.service;

import com.example.rediscache.domain.Order;
import com.example.rediscache.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Cacheable(value = "Order", key = "#orderId", cacheManager = "cacheManager", unless = "#orderId == ''") // 캐시가 존재하지 않으면 메서드 실행되고 리턴 데이터 캐시에 저장
    public Order getOrder(Long orderId) {
        System.out.println("getOrder 실행");
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("찾을 수 없음"));
    }

    @Override
    @CachePut(value = "Order", key = "#orderId", cacheManager = "cacheManager") // 캐시에 데이터를 넣어나 수정시 사용 (메서드에 리턴값이 캐시에 없으면 저장, 있을 경우 갱신)
    public Order updateOrder(Order order, Long orderId) {
        System.out.println("updateOrder 실행");
        Order orderData = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("찾을 수 없음"));
        if (orderData.getOrderStatus().equals("ready")) {
            orderData.setOrderStatus("processing");
        } else if (orderData.getOrderStatus().equals("processing")) {
            orderData.setOrderStatus("shipped");
        } else if (orderData.getOrderStatus().equals("shipped")) {
            orderData.setOrderStatus("ready");

        }

        return orderRepository.save(orderData);
    }

    @Override
    @CacheEvict(value = "Order", key = "#orderId", cacheManager = "cacheManager")
    public void deleteOrder(Long orderId) {
        System.out.println("캐시삭제");
        Order orderData = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("찾을 수 없음"));
        orderRepository.delete(orderData);
    }

    @Override
    @Cacheable(value = "Order", cacheManager = "cacheManager")
    public List<Order> getAllOrders() {
        System.out.println("getAllOrders 작동");
        return orderRepository.findAll();
    }
}
