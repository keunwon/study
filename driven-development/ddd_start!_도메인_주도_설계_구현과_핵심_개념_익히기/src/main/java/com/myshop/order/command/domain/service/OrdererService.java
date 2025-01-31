package com.myshop.order.command.domain.service;

import com.myshop.member.command.domain.model.MemberId;
import com.myshop.order.command.domain.model.Orderer;

public interface OrdererService {
    Orderer createOrderer(MemberId orderMemberId);
}
