package com.myshop.order.command.application.dto;

import com.myshop.member.command.domain.model.MemberId;
import com.myshop.order.command.domain.model.ShippingInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderRequest {
    private List<OrderProduct> orderProducts;
    private MemberId ordererMemberId;
    private ShippingInfo shippingInfo;
}
