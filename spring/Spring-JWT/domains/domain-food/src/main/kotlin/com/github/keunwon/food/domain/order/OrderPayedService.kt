package com.github.keunwon.food.domain.order

interface OrderPayedService {
    fun payOrder(orderId: Long)
}
