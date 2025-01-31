package com.keunwon.jpashop.repository.order.query;

import com.keunwon.jpashop.domain.Address;
import com.keunwon.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderFlatDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    private String itemName;
    private int orderPrice;
    private int count;


    public OrderFlatDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public OrderQueryDto toQueryDto() {
        return new OrderQueryDto(orderId, name, orderDate, orderStatus, address);
    }

    public OrderItemQueryDto toItemQueryDto() {
        return new OrderItemQueryDto(orderId, itemName, orderPrice, count);
    }
}
