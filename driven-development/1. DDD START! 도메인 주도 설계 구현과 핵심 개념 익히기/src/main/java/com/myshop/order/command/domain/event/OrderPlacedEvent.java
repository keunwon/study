package com.myshop.order.command.domain.event;

import com.myshop.order.command.domain.model.OrderLine;
import com.myshop.order.command.domain.model.Orderer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderPlacedEvent {
    private String number;
    private Orderer orderer;
    private List<OrderLine> orderLines;
    private LocalDateTime orderDate;
}
