package com.github.keunwon.food.order.domain

import com.github.keunwon.core.generic.money.Money

data class OrderDeliveredEvent(
    val orderId: Long,
    val shopId: Long,
    val totalPrice: Money,
) {
    constructor(order: Order) : this(order.id, order.shopId, order.calculateTotalPrice())
}

data class OrderPayedEvent(
    val orderId: Long,
) {
    constructor(order: Order) : this(order.id)
}
