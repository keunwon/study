package com.github.keunwon.food.service.shop

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.food.domain.shop.Menu
import com.github.keunwon.food.domain.shop.Shop

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
