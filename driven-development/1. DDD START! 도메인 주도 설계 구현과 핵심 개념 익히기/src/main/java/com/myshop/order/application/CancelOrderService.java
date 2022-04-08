package com.myshop.order.application;

import com.myshop.order.domain.model.Order;
import com.myshop.order.domain.model.OrderNo;
import com.myshop.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CancelOrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public void cancel(OrderNo orderNo) {
        Order order = orderRepository.findById(orderNo);

        if (order == null) { throw new NoOrderException(orderNo); }
        order.cancel();
    }
}
