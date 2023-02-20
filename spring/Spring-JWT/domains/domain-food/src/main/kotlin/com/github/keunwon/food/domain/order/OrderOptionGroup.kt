package com.github.keunwon.food.domain.order

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.corejpa.BaseEntity
import com.github.keunwon.food.domain.shop.OptionGroup
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity
@Table(name = "order_option_groups")
class OrderOptionGroup(
    @Column(name = "name")
    val name: String,

    @ElementCollection
    @CollectionTable(name = "order_options", joinColumns = [JoinColumn(name = "id")])
    val orderOptions: MutableList<OrderOption>,

    id: Long = 0L,
) : BaseEntity(id) {
    fun calculatePrice(): Money {
        return Money.sum(orderOptions) { it.price }
    }

    fun convertToOptionGroup(): OptionGroup {
        return OptionGroup(name, orderOptions.map { it.convertToOption() })
    }
}
