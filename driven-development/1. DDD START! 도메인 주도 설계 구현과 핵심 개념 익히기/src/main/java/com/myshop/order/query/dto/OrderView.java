package com.myshop.order.query.dto;

import com.myshop.member.domain.MemberId;
import com.myshop.order.domain.model.OrderNo;
import com.myshop.order.domain.model.OrderState;
import lombok.Getter;

@Getter
public class OrderView {
    private final String number;
    private final OrderState state;
    private final String memberName;
    private final String memberId;
    private final String productName;

    public OrderView(OrderNo number, OrderState state, String memberName, MemberId memberId, String productName) {
        this.number = number.getNumber();
        this.state = state;
        this.memberName = memberName;
        this.memberId = memberId.getId();
        this.productName = productName;
    }
}
