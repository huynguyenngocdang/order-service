package com.huynguyenngocdang.order_service.specification;

import com.huynguyenngocdang.order_service.dto.OrderCriteria;
import com.huynguyenngocdang.order_service.model.Order;
import com.huynguyenngocdang.order_service.utils.StringWorkerUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class OrderSpecification {
    public static Specification<Order> buildOrderSpecification(OrderCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(criteria.keySearch())) {
                predicates.add(cb.like(cb.lower(root.get(Order.Fields.orderNumber)), StringWorkerUtils.likeSqlTrimLowerCase(criteria.keySearch())));
                predicates.add(cb.like(cb.lower(root.get(Order.Fields.skuCode)), StringWorkerUtils.likeSqlTrimLowerCase(criteria.keySearch())));
            }
            if(criteria.priceMin() != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get(Order.Fields.price), criteria.priceMin()));
            if(criteria.priceMax() != null)
                predicates.add(cb.lessThanOrEqualTo(root.get(Order.Fields.price), criteria.priceMax()));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
