package com.myshop.order.command.domain.event;

import com.myshop.common.event.Event;
import lombok.Getter;

@Getter
public class OrderCanceledEvent extends Event {
    private String orderNumber;

    public OrderCanceledEvent(String number) {
        super();
        this.orderNumber = number;
    }
}
