package com.github.keunwon.food.delivery.domain

import com.github.keunwon.core.support.LogSupport
import com.github.keunwon.corejpa.getById
import com.github.keunwon.food.order.domain.OrderDeliveredEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CompleteDeliveryWithOrderDeliveredEventHandler(
    private val deliveryRepository: DeliveryRepository,
) {
    @Async
    @EventListener
    @Transactional
    fun handle(event: OrderDeliveredEvent) {
        log.info("> 배달 완료")
        deliveryRepository.getById(event.orderId).complete()
    }

    companion object : LogSupport
}
