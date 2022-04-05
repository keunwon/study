package com.myshop.order.command.domain;

import com.myshop.member.command.domain.MemberId;

public interface OrderService {
    Orderer createOrderer(MemberId memberId);
}
