package com.myshop.order.command.domain;

import com.myshop.order.command.domain.Canceller;
import com.myshop.order.command.domain.model.Order;

public interface CancelPolicy {
    boolean hasCancellationPermission(Order order, Canceller canceller);
}
