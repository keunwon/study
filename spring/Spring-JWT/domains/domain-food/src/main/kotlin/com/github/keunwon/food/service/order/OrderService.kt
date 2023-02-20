package com.github.keunwon.food.service.order

import com.github.keunwon.food.domain.order.OrderDeliveredService
import com.github.keunwon.food.domain.order.OrderPayedService
import com.github.keunwon.food.domain.order.OrderRepository
import com.github.keunwon.food.domain.order.OrderValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderValidator: OrderValidator,
    private val orderMapper: OrderMapper,
    private val orderDeliveredService: OrderDeliveredService,
    private val orderPayedService: OrderPayedService,
) {
    @Transactional
    fun place(cart: Cart) {
        val order = orderMapper.mapFrom(cart).apply {
            place(orderValidator)
        }
        orderRepository.save(order)
    }

    @Transactional
    fun payOrder(orderId: Long) {
        orderPayedService.payOrder(orderId)
    }
   
    @Transactional
    fun deliverOrder(orderId: Long) {
        orderDeliveredService.deliverOrder(orderId)
    }
}
