package com.myshop.order.command.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoOrderProductException extends RuntimeException {
    private final String productId;
}
