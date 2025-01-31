package com.myshop.order.query.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListRequest {
    private final int page;
    private final int size;
}
