package com.github.keunwon.food.order.domain

interface OrderPayedService {
    fun payOrder(orderId: Long)
}
