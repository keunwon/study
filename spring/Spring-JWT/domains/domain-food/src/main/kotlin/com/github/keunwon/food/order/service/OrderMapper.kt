package com.github.keunwon.food.order.service

import com.github.keunwon.food.domain.order.domain.Order
import com.github.keunwon.food.domain.order.domain.OrderLineItem
import com.github.keunwon.food.domain.order.domain.OrderOption
import com.github.keunwon.food.domain.order.domain.OrderOptionGroup
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OrderMapper {
    fun mapFrom(cart: Cart): Order {
        return Order(
            userId = cart.userId,
            shopId = cart.shopId,
            orderLineItems = cart.cartLineItems.map { toOrderLineItem(it) }.toMutableList(),
            orderedDateTime = LocalDateTime.now(),
        )
    }

    private fun toOrderLineItem(cartLineItem: CartLineItem): OrderLineItem {
        return OrderLineItem(
            menuId = cartLineItem.menuId,
            name = cartLineItem.name,
            count = cartLineItem.count,
            groups = cartLineItem.groups.map { toOrderOptionGroup(it) }.toMutableList(),
        )
    }

    private fun toOrderOptionGroup(cartOptionGroup: CartOptionGroup): OrderOptionGroup {
        return OrderOptionGroup(
            name = cartOptionGroup.name,
            orderOptions = cartOptionGroup.options.map { toOrderOption(it) }.toMutableList()
        )
    }

    private fun toOrderOption(cartOption: CartOption): OrderOption {
        return OrderOption(
            name = cartOption.name,
            price = cartOption.price,
        )
    }
}
