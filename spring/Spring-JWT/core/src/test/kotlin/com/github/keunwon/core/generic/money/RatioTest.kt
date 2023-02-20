package com.github.keunwon.core.generic.money

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class RatioTest : StringSpec({
    "전체 금액의 n% 반환한다" {
        forAll(
            row(Money.wons(10_000), 0.1, Money.wons(1_000)),
            row(Money.wons(20_000), 0.2, Money.wons(4_000)),
            row(Money.wons(100_000), 0.5, Money.wons(50_000)),
            row(Money.wons(100_000), 1.0, Money.wons(100_000)),
            row(Money.wons(100_000), 1.1, Money.wons(110_000)),
        ) { totalMoney, percent, result ->
            val ratio = Ratio.valueOf(percent)

            ratio.of(totalMoney) shouldBe result
        }
    }
})
