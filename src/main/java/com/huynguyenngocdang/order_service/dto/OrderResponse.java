package com.huynguyenngocdang.order_service.dto;

import java.math.BigDecimal;

public record OrderResponse(Long id, String orderNumber, String skuCode, BigDecimal price, BigDecimal quantity) {
}
