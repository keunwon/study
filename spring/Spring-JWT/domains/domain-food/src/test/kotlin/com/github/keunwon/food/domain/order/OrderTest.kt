package com.github.keunwon.food.domain.order

import com.github.keunwon.food.domain.shop.ShopBuilder
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class OrderTest : StringSpec({
    "주문 완료 상태에서 결재 완료 상태로 변경한다" {
        val order = OrderBuilder(
            status = OrderStatus.ORDERED,
        ).build()

        order.payed()

        order.status shouldBe OrderStatus.PAYED
    }

    "결재 완료 상태에서 배달완료 상태로 변경한다" {
        val order = OrderBuilder(
            shopId = ShopBuilder().build().id,
            status = OrderStatus.PAYED,
        ).build()

        order.delivered()

        order.status shouldBe OrderStatus.DELIVERED
    }
})
