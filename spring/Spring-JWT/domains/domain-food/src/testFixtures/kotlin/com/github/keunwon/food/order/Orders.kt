package com.github.keunwon.food.order

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.food.domain.order.domain.Order
import com.github.keunwon.food.domain.order.domain.OrderLineItem
import com.github.keunwon.food.domain.order.domain.OrderOption
import com.github.keunwon.food.domain.order.domain.OrderOptionGroup
import com.github.keunwon.food.domain.order.domain.OrderStatus
import java.time.LocalDateTime

data class OrderBuilder(
    val userId: Long = 1L,
    val shopId: Long = 1L,
    val orderLineItems: MutableList<OrderLineItem> = mutableListOf(
        OrderLineItemBuilder().build(),
    ),
    val orderedDateTime: LocalDateTime = LocalDateTime.now(),
    val status: OrderStatus = OrderStatus.NONE,
    val id: Long = 1L,
) {
    fun build(): Order {
        return Order(
            userId = userId,
            shopId = shopId,
            orderLineItems = orderLineItems,
            orderedDateTime = orderedDateTime,
            status = status,
            id = id,
        )
    }
}

data class OrderLineItemBuilder(
    val menuId: Long = 1L,
    val name: String = "삼겹살 1인 세트",
    val count: Int = 1,
    val groups: MutableList<OrderOptionGroup> = mutableListOf(
        OrderOptionGroupBuilder().build(),
        OrderOptionGroupBuilder(
            name = "맛 선택",
            orderOptions = mutableListOf(
                OrderOptionBuilder(
                    name = "매콤 맛",
                    price = Money.wons(1_000),
                ).build(),
            ),
        ).build(),
    ),
    val id: Long = 0,
) {
    fun build(): OrderLineItem {
        return OrderLineItem(
            menuId = menuId,
            name = name,
            count = count,
            groups = groups,
            id = id,
        )
    }
}

data class OrderOptionGroupBuilder(
    val name: String = "기본",
    val orderOptions: MutableList<OrderOption> = mutableListOf(
        OrderOptionBuilder().build(),
    ),
    val id: Long = 1L,
) {
    fun build(): OrderOptionGroup {
        return OrderOptionGroup(
            name = name,
            orderOptions = orderOptions,
            id = id,
        )
    }
}

data class OrderOptionBuilder(
    val name: String = "소(250)g",
    val price: Money = Money.wons(12_000),
) {
    fun build(): OrderOption {
        return OrderOption(
            name = name,
            price = price,
        )
    }
}
