package com.myshop.order.command.application.service;

import com.myshop.common.VersionConflictException;
import com.myshop.order.NoOrderException;
import com.myshop.order.command.application.dto.StartShippingRequest;
import com.myshop.order.command.domain.model.OrderNo;
import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StartShippingService {
    private final OrderRepository orderRepository;

    @Transactional
    public void startShipping(StartShippingRequest req) {
        Order order = orderRepository.findById(new OrderNo(req.getOrderNumber()))
                .orElseThrow(NoOrderException::new);

        if (order.matchVersion(req.getVersion())) {
            throw new VersionConflictException();
        }
        order.startShipping();
    }
}
