package com.github.keunwon.core.generic.money

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.math.BigDecimal

private class MoneyDeserializer : JsonDeserializer<Money>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Money = Money.wons(p.decimalValue)
}

private class MoneySerializer : JsonSerializer<Money>() {
    override fun serialize(value: Money, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.toString())
    }
}

@JsonDeserialize(using = MoneyDeserializer::class)
@JsonSerialize(using = MoneySerializer::class)
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

        if (amount.toDouble() != other.amount.toDouble()) return false

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

        fun <T> sum(bags: Collection<T>, block: (T) -> Money): Money {
            return bags.map { block(it) }.fold(ZERO) { acc, money -> acc + money }
        }
    }
}
