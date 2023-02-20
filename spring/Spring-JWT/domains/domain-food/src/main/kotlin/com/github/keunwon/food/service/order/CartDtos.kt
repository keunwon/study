package com.github.keunwon.food.service.order

import com.github.keunwon.core.generic.money.Money

data class Cart(
    val shopId: Long,
    val userId: Long,
    val cartLineItems: List<CartLineItem>,
)

data class CartLineItem(
    val menuId: Long,
    val name: String,
    val count: Int,
    val groups: List<CartOptionGroup>,
)

data class CartOptionGroup(
    val name: String,
    val options: List<CartOption>,
)

data class CartOption(
    val name: String,
    val price: Money,
)
