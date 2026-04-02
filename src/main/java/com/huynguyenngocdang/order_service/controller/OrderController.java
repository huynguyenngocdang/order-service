package com.huynguyenngocdang.order_service.controller;

import com.huynguyenngocdang.commons.common.PageResponse;
import com.huynguyenngocdang.commons.common.ResponseApi;
import com.huynguyenngocdang.order_service.dto.OrderCriteria;
import com.huynguyenngocdang.order_service.dto.OrderRequest;
import com.huynguyenngocdang.order_service.dto.OrderResponse;
import com.huynguyenngocdang.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<OrderResponse>> findOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseApi.success(orderService.getOrderById(id)));
    }

    @GetMapping
    public ResponseEntity<ResponseApi<PageResponse<OrderResponse>>> findAllOrders(
            @RequestParam(required = false) String keySearch,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
            ) {
        OrderCriteria criteria = new OrderCriteria(keySearch, priceMin, priceMax);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(ResponseApi.success(orderService.getOrders(criteria, pageable)));
    }

    @PostMapping
    public ResponseEntity<ResponseApi<OrderResponse>> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(ResponseApi.success(orderService.createOrder(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<OrderResponse>> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        return ResponseEntity.ok(ResponseApi.success(orderService.updateOrder(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<Void>> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(ResponseApi.success(null));
    }

}
