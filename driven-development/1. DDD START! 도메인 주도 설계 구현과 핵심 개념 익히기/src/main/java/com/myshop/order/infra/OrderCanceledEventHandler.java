package com.myshop.order.infra;

import com.myshop.order.command.application.RefundService;
import com.myshop.order.command.domain.event.OrderCanceledEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class OrderCanceledEventHandler {
    private final RefundService refundService;

    @Async
    @TransactionalEventListener(classes = OrderCanceledEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderCanceledEvent event) {
        refundService.refund(event.getOrderNumber());
    }
}
