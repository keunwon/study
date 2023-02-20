package com.github.keunwon.food.billing.domain

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.corejpa.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "billings")
class Billing(
    @Column(name = "shop_id")
    val shopId: Long,

    @Column(name = "commission")
    var commission: Money = Money.ZERO,

    id: Long = 0L,
) : BaseEntity(id) {
    fun billCommissionFee(commission: Money) {
        this.commission += commission
    }
}
