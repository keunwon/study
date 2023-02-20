package com.github.keunwon.food.order.domain

interface OrderDeliveredService {
    fun deliverOrder(orderId: Long)
}
