package com.github.keunwon.food.billing.domain

import com.github.keunwon.core.support.LogSupport
import com.github.keunwon.corejpa.getById
import com.github.keunwon.food.order.domain.OrderDeliveredEvent
import com.github.keunwon.food.shop.domain.ShopRepository
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class BillShopWithOrderDeliveredEventHandler(
    private val shopRepository: ShopRepository,
    private val billingRepository: BillingRepository,
) {
    @Async
    @EventListener
    @Transactional
    fun handle(event: OrderDeliveredEvent) {
        log.info("> 가게 배달완료")
        val shop = shopRepository.getById(event.shopId)
        val billing = billingRepository.findByShopId(event.shopId) ?: Billing(event.shopId)
        billing.billCommissionFee(shop.calculateCommissionFee(event.totalPrice))
    }

    companion object : LogSupport
}
