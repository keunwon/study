package com.myshop.order.domain.model;

import lombok.Getter;

@Getter
public enum OrderState {
    PAYMENT_WAITING("결제 대기"),
    PREPARING("상품 준비"),
    SHIPPED("출고완료"),
    DELIVERING("배송중"),
    DELIVER_COMPLETED("배송완료"),
    CANCELED("주문 취소");

    final String name;

    OrderState(String name) {
        this.name = name;
    }
}
