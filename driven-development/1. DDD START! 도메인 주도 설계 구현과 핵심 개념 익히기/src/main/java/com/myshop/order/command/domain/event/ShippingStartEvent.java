package com.myshop.order.command.domain.event;

import com.myshop.common.event.Event;
import lombok.Getter;

@Getter
public class ShippingStartEvent extends Event {
    private String orderNumber;
    public ShippingStartEvent(String number) {
        super();
        this.orderNumber = number;
    }
}
