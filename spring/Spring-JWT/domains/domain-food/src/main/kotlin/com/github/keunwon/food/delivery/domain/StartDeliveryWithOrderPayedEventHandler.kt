package com.github.keunwon.food.delivery.domain

import com.github.keunwon.core.support.LogSupport
import com.github.keunwon.food.order.domain.OrderPayedEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class StartDeliveryWithOrderPayedEventHandler(
    private val deliveryRepository: DeliveryRepository,
) {
    @Async
    @EventListener
    @Transactional
    fun handle(event: OrderPayedEvent) {
        log.info("> 배달 시작")
        val delivery = Delivery.started(event.orderId)
        deliveryRepository.save(delivery)
    }

    companion object : LogSupport
}
