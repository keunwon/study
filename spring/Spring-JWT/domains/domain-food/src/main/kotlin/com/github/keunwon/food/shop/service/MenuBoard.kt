package com.github.keunwon.food.shop.service

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.food.shop.domain.Menu
import com.github.keunwon.food.shop.domain.Shop

data class MenuBoard(
    private val shopId: Long,
    private val shopName: String,
    private val open: Boolean,
    private val minOrderAmount: Money,
    private val menuItems: List<MenuItem>,
) {
    constructor(shop: Shop, menus: List<Menu>) : this(
        shop.id,
        shop.name,
        shop.open,
        shop.minOrderAmount,
        menus.map { MenuItem(it) },
    )
}

data class MenuItem(
    val menuId: Long,
    val menuName: String,
    val menuBasePrice: Money,
    val menuDescription: String,
) {
    constructor(menu: Menu) : this(menu.id, menu.name, menu.basePrice(), menu.description)
}
