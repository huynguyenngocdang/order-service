package com.huynguyenngocdang.order_service.service;

import com.huynguyenngocdang.commons.common.PageResponse;
import com.huynguyenngocdang.order_service.dto.OrderCriteria;
import com.huynguyenngocdang.order_service.dto.OrderRequest;
import com.huynguyenngocdang.order_service.dto.OrderResponse;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse getOrderById(Long id);
    PageResponse<OrderResponse> getOrders(OrderCriteria criteria, Pageable pageable);
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(Long id, OrderRequest orderRequest);
    void deleteOrder(Long id);
}
