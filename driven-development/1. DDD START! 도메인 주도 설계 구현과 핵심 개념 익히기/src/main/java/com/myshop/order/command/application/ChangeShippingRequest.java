package com.myshop.order.command.application;

import com.myshop.order.command.domain.model.ShippingInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeShippingRequest {
    private final String number;
    private final ShippingInfo shippingInfo;
}
