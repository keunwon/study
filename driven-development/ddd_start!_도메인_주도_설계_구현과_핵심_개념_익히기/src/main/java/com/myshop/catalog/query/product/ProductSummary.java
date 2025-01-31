package com.myshop.catalog.query.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductSummary {
    private final String id;
    private final String name;
    private final int price;
    private final String image;
}
