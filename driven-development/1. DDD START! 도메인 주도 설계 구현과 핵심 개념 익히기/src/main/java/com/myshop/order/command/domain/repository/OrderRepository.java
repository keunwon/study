package com.myshop.order.command.domain.repository;

import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.model.OrderNo;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public interface OrderRepository extends Repository<Order, OrderNo> {

    Optional<Order> findById(OrderNo id);

    void save(Order order);

    default OrderNo nextOrderNo() {
        int randomNo = ThreadLocalRandom.current().nextInt(900000) + 100000;
        String number = String.format("%tY%<tm%<td%<tH-%d", new Date(), randomNo);
        return new OrderNo(number);
    }
}
