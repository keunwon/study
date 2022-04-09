package com.myshop.order.command.domain.model;

import com.myshop.catalog.command.domain.product.model.ProductId;
import com.myshop.common.jpa.converter.MoneyConverter;
import com.myshop.common.model.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OrderLine {
    @Embedded
    private ProductId productId;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "price")
    private Money price;
    @Column(name = "quantity")
    private int quantity;
    @Convert(converter = MoneyConverter.class)
    @Column(name = "amounts")
    private Money amounts;

    public OrderLine(ProductId productId, Money price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return new Money(price.getValue() * quantity);
    }
}
