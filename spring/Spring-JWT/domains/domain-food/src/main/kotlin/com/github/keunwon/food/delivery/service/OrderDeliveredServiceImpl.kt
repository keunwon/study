package com.github.keunwon.food.delivery.service

import com.github.keunwon.corejpa.getById
import com.github.keunwon.food.domain.order.domain.OrderDeliveredService
import com.github.keunwon.food.domain.order.domain.OrderPayedService
import com.github.keunwon.food.domain.order.domain.OrderRepository
import com.github.keunwon.food.domain.shop.domain.ShopRepository
import org.springframework.stereotype.Component

@Component
class OrderDeliveredServiceImpl(
    private val orderRepository: OrderRepository,
    private val shopRepository: ShopRepository,
    private val deliveryRepository: com.github.keunwon.food.delivery.domain.DeliveryRepository,
) : OrderPayedService, OrderDeliveredService {
    override fun deliverOrder(orderId: Long) {
        val order = orderRepository.getById(orderId)
        val shop = shopRepository.getById(order.id)
        val delivery = deliveryRepository.getById(orderId)

        order.delivered()
        shop.billCommissionFee(order.calculateTotalPrice())
        delivery.complete()
    }

    override fun payOrder(orderId: Long) {
        val order = orderRepository.getById(orderId).apply { payed() }
        val delivery = com.github.keunwon.food.delivery.domain.Delivery.started(order.id)
        deliveryRepository.save(delivery)
    }
}
