package com.github.keunwon.food.delivery

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.core.generic.money.Ratio
import com.github.keunwon.corejpa.getById
import com.github.keunwon.food.domain.order.OrderBuilder
import com.github.keunwon.food.domain.order.OrderLineItemBuilder
import com.github.keunwon.food.domain.order.OrderOptionBuilder
import com.github.keunwon.food.domain.order.OrderOptionGroupBuilder
import com.github.keunwon.food.domain.order.domain.OrderRepository
import com.github.keunwon.food.domain.order.domain.OrderStatus
import com.github.keunwon.food.domain.shop.ShopBuilder
import com.github.keunwon.food.domain.shop.domain.ShopRepository
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class OrderDeliveredServiceImplTest : StringSpec({
    val orderRepository = mockk<OrderRepository>()
    val shopRepository = mockk<ShopRepository>()
    val deliveryRepository = mockk<com.github.keunwon.food.delivery.domain.DeliveryRepository>()

    val orderDeliveredServiceImpl = com.github.keunwon.food.delivery.service.OrderDeliveredServiceImpl(orderRepository,
        shopRepository,
        deliveryRepository)

    "주문 완료" {
        val shop = ShopBuilder(
            commission = Money.wons(10_000),
            commissionRate = Ratio.valueOf(0.1),
        ).build()
        val order = OrderBuilder(
            orderLineItems = mutableListOf(
                OrderLineItemBuilder(
                    groups = mutableListOf(
                        OrderOptionGroupBuilder(
                            orderOptions = mutableListOf(
                                OrderOptionBuilder(
                                    price = Money.wons(10_000),
                                ).build(),
                            ),
                        ).build(),
                    ),
                ).build(),
            ),
        ).build()
        val delivery = DeliveryBuilder().build()

        every { orderRepository.getById(order.id) } returns order
        every { shopRepository.getById(shop.id) } returns shop
        every { deliveryRepository.getById(delivery.id) } returns delivery

        orderDeliveredServiceImpl.deliverOrder(order.id)

        order.status shouldBe OrderStatus.DELIVERED
        delivery.status shouldBe com.github.keunwon.food.delivery.domain.DeliveryStatus.DELIVERED
        shop.commission shouldBe Money.wons(11_000)
    }

    isolationMode = IsolationMode.InstancePerLeaf
})
