package com.github.keunwon.food.domain.shop

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.corejpa.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "option_specs")
class OptionSpecification(
    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: Money,

    id: Long = 0,
) : BaseEntity(id) {
    fun isSatisfiedBy(option: Option): Boolean {
        return Option(name, price) == option
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OptionSpecification

        if (name != other.name) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}
