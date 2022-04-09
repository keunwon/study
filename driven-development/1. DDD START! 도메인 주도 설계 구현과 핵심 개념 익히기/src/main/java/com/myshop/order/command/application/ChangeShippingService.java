package com.myshop.order.command.application;

import com.myshop.order.NoOrderException;
import com.myshop.order.command.domain.model.OrderNo;
import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeShippingService {
    private final OrderRepository orderRepository;

    @Transactional
    public void changeShipping(ChangeShippingRequest changeReq) {
        Order order = orderRepository.findById(OrderNo.of(changeReq.getNumber()))
                .orElseThrow(NoOrderException::new);
        order.changeShippingInfo(changeReq.getShippingInfo());
    }
}
