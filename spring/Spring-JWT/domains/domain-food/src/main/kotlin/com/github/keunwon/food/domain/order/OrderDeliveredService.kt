package com.github.keunwon.food.domain.order

interface OrderDeliveredService {
    fun deliverOrder(orderId: Long)
}
