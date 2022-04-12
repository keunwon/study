package com.myshop.order.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StartShippingRequest {
    private final String orderNumber;
    private final long version;
}
