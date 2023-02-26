package com.github.keunwon.food.shop.service

import com.github.keunwon.core.generic.money.Money

data class MenuRegistration(
    val shopId: Long,
    val name: String,
    val description: String,
    val menuOptionGroups: List<MenuOptionGroup>,
)

data class MenuOptionGroup(
    val name: String,
    val exclusive: Boolean,
    val basic: Boolean,
    val menuOptions: List<MenuOption>,
)

data class MenuOption(
    val name: String,
    val price: Money,
)
