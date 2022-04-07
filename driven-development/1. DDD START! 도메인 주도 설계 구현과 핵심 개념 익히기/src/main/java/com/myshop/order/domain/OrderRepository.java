package com.myshop.order.domain;

import java.util.List;

public interface OrderRepository {
    Order findById(OrderNo no);
    void save(Order order);

    List<Order> findByOrdererId(String ordererId, int startRow, int size);

    void delete(Order order);
}
