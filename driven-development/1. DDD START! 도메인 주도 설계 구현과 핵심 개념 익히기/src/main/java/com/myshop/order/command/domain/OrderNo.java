package com.myshop.order.command.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "number")
@Embeddable
public class OrderNo implements Serializable {
    @Column(name = "order_number")
    private String number;

    public static OrderNo of(String number) {
        return new OrderNo(number);
    }
}
