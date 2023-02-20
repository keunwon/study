package com.github.keunwon.food.service.order

import com.github.keunwon.food.domain.order.Order
import com.github.keunwon.food.domain.order.OrderLineItem
import com.github.keunwon.food.domain.order.OrderOption
import com.github.keunwon.food.domain.order.OrderOptionGroup
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
