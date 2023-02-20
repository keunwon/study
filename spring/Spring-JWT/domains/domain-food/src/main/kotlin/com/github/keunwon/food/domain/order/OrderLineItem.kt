package com.github.keunwon.food.domain.order

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.corejpa.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "order_line_items")
class OrderLineItem(
    @Column(name = "menuId")
    val menuId: Long,

    @Column(name = "food_name")
    val name: String,

    @Column(name = "food_count")
    val count: Int,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    val groups: MutableList<OrderOptionGroup>,

    id: Long = 0L,
) : BaseEntity(id) {
    fun calculatePrice(): Money {
        return Money.sum(groups) { it.calculatePrice() }
    }
}
