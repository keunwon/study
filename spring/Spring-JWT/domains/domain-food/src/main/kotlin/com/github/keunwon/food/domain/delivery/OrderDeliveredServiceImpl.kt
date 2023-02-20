package com.github.keunwon.food.domain.delivery

import com.github.keunwon.corejpa.getById
import com.github.keunwon.food.domain.order.OrderDeliveredService
import com.github.keunwon.food.domain.order.OrderPayedService
import com.github.keunwon.food.domain.order.OrderRepository
import com.github.keunwon.food.domain.shop.ShopRepository
import org.springframework.stereotype.Component

@Component
class OrderDeliveredServiceImpl(
    private val orderRepository: OrderRepository,
    private val shopRepository: ShopRepository,
    private val deliveryRepository: DeliveryRepository,
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
        val delivery = Delivery.started(order.id)
        deliveryRepository.save(delivery)
    }
}
