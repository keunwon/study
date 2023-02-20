package com.github.keunwon.food.order.domain

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.food.shop.domain.Option
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class OrderOption(
    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: Money,
) {
    fun convertToOption(): Option {
        return Option(name, price)
    }
}
