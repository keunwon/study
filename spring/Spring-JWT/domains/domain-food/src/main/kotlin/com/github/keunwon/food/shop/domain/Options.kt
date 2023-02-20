package com.github.keunwon.food.shop.domain

import com.github.keunwon.core.generic.money.Money

data class OptionGroup(
    val name: String,
    val options: List<Option>,
)

data class Option(
    val name: String,
    val price: Money,
)

