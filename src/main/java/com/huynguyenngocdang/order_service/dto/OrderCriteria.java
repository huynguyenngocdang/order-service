package com.huynguyenngocdang.order_service.dto;

import java.math.BigDecimal;

public record OrderCriteria(String keySearch, BigDecimal priceMin, BigDecimal priceMax) {
}
