package com.myshop.order.query.application;

import com.myshop.catalog.query.product.ProductData;
import com.myshop.order.command.domain.model.OrderLine;
import lombok.Getter;

@Getter
public class OrderLineDetail {
    private final String productId;
    private final int price;
    private final int quantity;
    private final int amounts;
    private final String productName;
    private final String productImagePath;

    public OrderLineDetail(OrderLine orderLine, ProductData product) {
        productId = orderLine.getProductId().getId();
        price = orderLine.getPrice().getValue();
        quantity = orderLine.getQuantity();
        amounts = orderLine.getAmounts().getValue();
        productName = product.getName();
        productImagePath = product.getFirstImageThumbnailPath();
    }
}
