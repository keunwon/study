package com.github.keunwon.core.generic.money

class Ratio(val rate: Double) {
    fun of(price: Money): Money = price.times(rate)

    companion object {
        fun valueOf(rate: Double): Ratio = Ratio(rate)
    }
}
