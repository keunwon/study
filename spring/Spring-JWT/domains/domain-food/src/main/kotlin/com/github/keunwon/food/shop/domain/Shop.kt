package com.github.keunwon.food.shop.domain

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.core.generic.money.Ratio
import com.github.keunwon.corejpa.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "shops")
class Shop(
    @Column(name = "name")
    val name: String,

    @Column(name = "open")
    var open: Boolean,

    @Column(name = "min_order_amount")
    val minOrderAmount: Money,

    @Column(name = "commission_rate")
    var commissionRate: Ratio = Ratio.valueOf(0.0),

    @Column(name = "commission")
    var commission: Money = Money.ZERO,

    id: Long = 0L,
) : BaseEntity(id) {
    fun isValidOrderAmount(amount: Money): Boolean {
        return minOrderAmount <= amount
    }

    fun open() {
        open = true
    }

    fun close() {
        open = false
    }

    fun modifyCommissionRate(commissionRate: Ratio) {
        this.commissionRate = commissionRate
    }

    fun billCommissionFee(price: Money) {
        commission += commissionRate.of(price)
    }
}
