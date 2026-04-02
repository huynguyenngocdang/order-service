package com.huynguyenngocdang.order_service.service.impl;

import com.huynguyenngocdang.commons.common.PageResponse;
import com.huynguyenngocdang.order_service.dto.OrderCriteria;
import com.huynguyenngocdang.order_service.dto.OrderRequest;
import com.huynguyenngocdang.order_service.dto.OrderResponse;
import com.huynguyenngocdang.order_service.mapper.OrderMapper;
import com.huynguyenngocdang.order_service.model.Order;
import com.huynguyenngocdang.order_service.repository.OrderRepository;
import com.huynguyenngocdang.order_service.service.OrderService;
import com.huynguyenngocdang.order_service.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse getOrderById(Long id) {
        log.info("Get order by id: {}", id);
        return orderMapper.toOrderResponse(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public PageResponse<OrderResponse> getOrders(OrderCriteria criteria, Pageable pageable) {
        log.info("Get orders with criteria: {}", criteria);
        Page<Order> orders = orderRepository.findAll(OrderSpecification.buildOrderSpecification(criteria), pageable);
        return PageResponse.of(orders.map(orderMapper::toOrderResponse));
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        log.info("Create order: {}", orderRequest);
        Order order = orderMapper.toOrder(orderRequest);
        if(orderRepository.findByOrderNumber(order.getOrderNumber()).isPresent()) throw new IllegalArgumentException("Order number already exists");
        if(!StringUtils.hasText(order.getOrderNumber())) order.setOrderNumber(UUID.randomUUID().toString());
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElseThrow();
        if(orderRepository.findByOrderNumber(orderRequest.orderNumber()).isPresent()) throw new IllegalArgumentException("Order number already exists");
        order = orderMapper.updateOrder(order, orderRequest);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.findById(id).orElseThrow();
        orderRepository.deleteById(id);
    }

}
