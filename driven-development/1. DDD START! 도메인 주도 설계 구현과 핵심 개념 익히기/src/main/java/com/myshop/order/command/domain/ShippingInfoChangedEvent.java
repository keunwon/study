package com.myshop.order.command.domain;

import lombok.Getter;

@Getter
public class ShippingInfoChangedEvent {
    private OrderNo orderNo;
    private ShippingInfo newShippingInfo;
    private long timestamp;

    public ShippingInfoChangedEvent(OrderNo orderNo, ShippingInfo newShippingInfo) {
        this.orderNo = orderNo;
        this.newShippingInfo = newShippingInfo;
        this.timestamp = System.currentTimeMillis();
    }
}
