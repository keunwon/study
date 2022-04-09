package com.myshop.order.command.application;

import com.myshop.common.event.Events;
import com.myshop.order.NoOrderException;
import com.myshop.order.command.domain.CancelPolicy;
import com.myshop.order.command.domain.Canceller;
import com.myshop.order.command.domain.event.OrderCanceledEvent;
import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.model.OrderNo;
import com.myshop.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CancelOrderService {
    private final OrderRepository orderRepository;
    private final CancelPolicy cancelPolicy;

    private final RefundService refundService;

    @Transactional
    public void cancel(OrderNo orderNo, Canceller canceller) {
        //Events.handler((OrderCanceledEvent event) -> refundService.refund(event.getOrderNumber()));
        Events.handleAsync((OrderCanceledEvent event) -> refundService.refund(event.getOrderNumber()));

        Order order = orderRepository.findById(orderNo)
                .orElseThrow(NoOrderException::new);

        if (!cancelPolicy.hasCancellationPermission(order, canceller)) {
            throw new NoCancellablePermission();
        }
        order.cancel();
    }
}
