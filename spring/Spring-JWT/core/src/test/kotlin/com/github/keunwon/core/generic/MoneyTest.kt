package com.github.keunwon.core.generic

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MoneyTest : StringSpec({
    "plus(+)" {
        forAll(
            row(Money.wons(1000L), Money.wons(1000L), Money.wons(2000L)),
            row(Money.wons(10000L), Money.wons(4500L), Money.wons(14500L)),
            row(Money.wons(100_000_001), Money.wons(5_432_011), Money.wons(105_432_012)),
        ) { money1, money2, result ->
            val value = money1 + money2
            value shouldBe result
        }
    }

    "minus(-)" {
        forAll(
            row(Money.wons(1000L), Money.wons(500L), Money.wons(500L)),
            row(Money.wons(1000L), Money.ZERO, Money.wons(1000L)),
            row(Money.wons(4_000_000), Money.wons(333_333), Money.wons(3_666_667L)),
        ) { money1, money2, result ->
            val value = money1 - money2
            value shouldBe result
        }
    }

    "times(*)" {
        forAll(
            row(Money.wons(1000L), 3.0, Money.wons(3000.0)),
            row(Money.wons(333_000L), 5.0, Money.wons(1_665_000.0)),
        ) { money, percent, result ->
            val value = money * percent
            value shouldBe result
        }
    }

    "div(/)" {
        forAll(
            row(Money.wons(1_000_000L), 10.0, Money.wons(100_000L)),
            row(Money.wons(3_000_000), 20.0, Money.wons(150_000L)),
        ) { money1, divisor, result ->
            val value = money1 / divisor
            value shouldBe result
        }
    }

    "비교 (compare)" {
        forAll(
            row(Money.wons(1L), Money.wons(2L), true),
            row(Money.wons(100L), Money.wons(50L), false),
            row(Money.wons(100L), Money.wons(100L), false),
        ) { money1, money2, result ->
            val value = money1 < money2
            value shouldBe result
        }
    }

    "정렬" {
        val sorted = listOf(
            Money.wons(5L),
            Money.wons(3L),
            Money.wons(1L),
            Money.wons(4L),
            Money.ZERO,
        ).sorted()

        sorted.map { it.toString() } shouldBe listOf("0 원", "1 원", "3 원", "4 원", "5 원")
    }
})
