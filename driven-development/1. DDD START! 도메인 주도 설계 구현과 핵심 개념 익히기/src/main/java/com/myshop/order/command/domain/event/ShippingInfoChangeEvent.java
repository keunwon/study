package com.myshop.order.command.domain.event;

import com.myshop.order.command.domain.model.OrderNo;
import com.myshop.order.command.domain.model.ShippingInfo;
import lombok.Getter;

@Getter
public class ShippingInfoChangeEvent {
    private final OrderNo number;
    private final ShippingInfo newShippingInfo;
    private long timestamp;

    public ShippingInfoChangeEvent(OrderNo number, ShippingInfo newShippingInfo) {
        this.number = number;
        this.newShippingInfo = newShippingInfo;
        this.timestamp = System.currentTimeMillis();
    }
}
