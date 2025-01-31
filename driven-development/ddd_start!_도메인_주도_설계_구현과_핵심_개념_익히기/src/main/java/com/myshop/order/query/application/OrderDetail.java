package com.myshop.order.query.application;

import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.model.OrderState;
import com.myshop.order.command.domain.model.Orderer;
import com.myshop.order.command.domain.model.ShippingInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDetail {
    private final String number;
    private long version;
    private final Orderer orderer;
    private final ShippingInfo shippingInfo;
    private final OrderState state;
    private final int totalAmounts;
    private List<OrderLineDetail> orderLines;
    private final boolean notYetShipped;

    public OrderDetail(Order order, List<OrderLineDetail> orderLines) {
        number = order.getNumber().getNumber();
        version = order.getVersion();
        orderer = order.getOrderer();
        shippingInfo = order.getShippingInfo();
        state = order.getState();
        totalAmounts = order.getTotalAmounts().getValue();
        this.orderLines = orderLines;
        notYetShipped = order.isNotYetShipped();
    }
}
