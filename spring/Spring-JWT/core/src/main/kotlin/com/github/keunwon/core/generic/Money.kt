package com.github.keunwon.core.generic

import java.math.BigDecimal

class Money private constructor(val amount: BigDecimal) : Comparable<Money> {
    operator fun plus(amount: Money): Money = Money(this.amount.plus(amount.amount))

    operator fun minus(amount: Money): Money = Money(this.amount.minus(amount.amount))

    operator fun times(percent: Double): Money = Money(this.amount.times(BigDecimal.valueOf(percent)))

    operator fun div(divisor: Double): Money = Money(this.amount.div(BigDecimal.valueOf(divisor)))


    override fun compareTo(other: Money): Int {
        return this.amount.compareTo(other.amount)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if (amount != other.amount) return false

        return true
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }

    override fun toString(): String = "$amount 원"

    companion object {
        val ZERO = wons(0L)

        fun wons(amount: Long) = Money(BigDecimal.valueOf(amount))
        fun wons(amount: Double) = Money(BigDecimal.valueOf(amount))
        fun wons(amount: BigDecimal) = Money(amount)
    }
}
