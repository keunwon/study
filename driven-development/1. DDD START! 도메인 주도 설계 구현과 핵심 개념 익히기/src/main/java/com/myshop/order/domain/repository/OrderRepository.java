package com.myshop.order.domain.repository;

import com.myshop.order.domain.model.Order;
import com.myshop.order.domain.model.OrderNo;

import java.util.List;

public interface OrderRepository {
    Order findById(OrderNo no);
    void save(Order order);

    List<Order> findByOrdererId(String ordererId, int startRow, int size);

    void delete(Order order);
}
