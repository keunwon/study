package com.myshop.order.query.dao;

import com.myshop.order.query.dto.OrderSummary;
import com.myshop.order.query.dto.OrderSummary_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSummarySpecs {

    public static Specification<OrderSummary> ordererId(String ordererId) {
        return (root, query, cb) ->
                cb.equal(root.<String>get("ordererId"), ordererId);
    }

    public static Specification<OrderSummary> orderDateBetween(LocalDateTime from, LocalDateTime to) {
        return (root, query, cb) ->
                cb.between(root.get(OrderSummary_.orderDate), from, to);
    }
}
