package com.huynguyenngocdang.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest (String orderNumber, String skuCode, BigDecimal price, BigDecimal quantity) {
}
