package com.huynguyenngocdang.order_service.mapper;

import com.huynguyenngocdang.order_service.dto.OrderRequest;
import com.huynguyenngocdang.order_service.dto.OrderResponse;
import com.huynguyenngocdang.order_service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
    Order toOrder(OrderRequest orderRequest);
    Order updateOrder(@MappingTarget Order order, OrderRequest request);
}
