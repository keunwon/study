package com.github.keunwon.food.order.service

import com.github.keunwon.corejpa.getById
import com.github.keunwon.food.order.domain.OrderRepository
import com.github.keunwon.food.order.domain.OrderValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderValidator: OrderValidator,
    private val orderMapper: OrderMapper,
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
        val order = orderRepository.getById(orderId).apply { delivered() }
        orderRepository.save(order)
    }

    @Transactional
    fun deliverOrder(orderId: Long) {
        val order = orderRepository.getById(orderId).apply { delivered() }
        orderRepository.save(order)
    }
}
