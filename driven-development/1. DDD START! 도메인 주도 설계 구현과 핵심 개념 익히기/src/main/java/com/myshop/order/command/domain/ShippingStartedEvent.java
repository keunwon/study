package com.myshop.order.command.domain;

import lombok.Getter;

@Getter
public class ShippingStartedEvent {
    private String orderNumber;

    public ShippingStartedEvent(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
