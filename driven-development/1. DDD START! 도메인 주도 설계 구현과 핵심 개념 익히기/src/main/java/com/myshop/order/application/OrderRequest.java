package com.myshop.order.application;

import com.myshop.member.domain.MemberId;
import com.myshop.order.domain.model.ShippingInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderRequest {
    private List<OrderProduct> orderProducts;
    private MemberId ordererMemberId;
    private ShippingInfo shippingInfo;
}
