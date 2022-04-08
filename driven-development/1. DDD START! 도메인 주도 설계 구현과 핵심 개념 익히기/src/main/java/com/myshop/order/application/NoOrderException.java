package com.myshop.order.application;

import com.myshop.order.domain.model.OrderNo;

public class NoOrderException extends RuntimeException {
    public NoOrderException(OrderNo orderNo) {
    }
}
