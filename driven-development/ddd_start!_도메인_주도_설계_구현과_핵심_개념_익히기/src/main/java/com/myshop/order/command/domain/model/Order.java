package com.myshop.order.command.domain.model;

import com.myshop.common.event.Events;
import com.myshop.common.jpa.converter.MoneyConverter;
import com.myshop.common.model.Money;
import com.myshop.order.command.domain.event.OrderCanceledEvent;
import com.myshop.order.command.domain.event.OrderPlacedEvent;
import com.myshop.order.command.domain.event.ShippingInfoChangeEvent;
import com.myshop.order.command.domain.event.ShippingStartEvent;
import com.myshop.order.command.domain.exception.AlreadyShippedException;
import com.myshop.order.command.domain.exception.OrderAlreadyCanceledException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Access(AccessType.FIELD)
@Table(name = "purchase_order")
public class Order {
    @EmbeddedId
    private OrderNo number;

    @Version
    private long version;

    @Embedded
    private Orderer orderer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_number"))
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "total_amounts")
    private Money totalAmounts;

    @Embedded
    private ShippingInfo shippingInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OrderState state;

    @Column(name = "order_date")
    private LocalDateTime orderData;

    public Order(OrderNo number, Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo, OrderState state) {
        setNumber(number);
        setOrderer(orderer);
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        this.state = state;
        this.orderData = LocalDateTime.now();
        Events.raise(new OrderPlacedEvent(number.getNumber(), orderer, orderLines, orderData));
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
        Events.raise(new ShippingInfoChangeEvent(number, newShippingInfo));
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
        Events.raise(new OrderCanceledEvent(number.getNumber()));
    }

    public boolean matchVersion(long version) {
        return this.version == version;
    }

    public void startShipping() {
        verifyShippableState();
        this.state = OrderState.SHIPPED;
        Events.raise(new ShippingStartEvent(number.getNumber()));
    }

    public boolean isNotYetShipped() {
        return OrderState.PAYMENT_WAITING != state && OrderState.PREPARING != state;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoeOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void setNumber(OrderNo number) {
        if (number == null) {
            throw new IllegalArgumentException("no number");
        }
        this.number = number;
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) {
            throw new IllegalArgumentException("no orderer");
        }
        this.orderer = orderer;
    }

    private void setShippingInfo(ShippingInfo newShippingInfo) {
        if (newShippingInfo == null) {
            throw new IllegalArgumentException("no shippingInfo");
        }
        this.shippingInfo = newShippingInfo;
    }

    private void verifyAtLeastOneOrMoeOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void verifyNotYetShipped() {
        if (!isNotYetShipped()) {
            throw new AlreadyShippedException();
        }
    }

    private void verifyShippableState() {
        verifyNotYetShipped();
        verifyNotCanceled();
    }

    private void verifyNotCanceled() {
        if (OrderState.CANCELED == state) {
            throw new OrderAlreadyCanceledException();
        }
    }

    private void calculateTotalAmounts() {
        int sum = orderLines.stream()
                .mapToInt(x -> x.getAmounts().getValue())
                .sum();
        this.totalAmounts = new Money(sum);
    }
}
