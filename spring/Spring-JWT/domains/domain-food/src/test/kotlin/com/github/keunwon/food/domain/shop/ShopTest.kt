package com.github.keunwon.food.domain.shop

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.core.generic.money.Ratio
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ShopTest : StringSpec({
    "주문 금액이 최소금액보다 작으면 false 반환한다" {
        val shop = ShopBuilder(minOrderAmount = Money.wons(15_900)).build()

        listOf(
            Money.wons(10_000),
            Money.wons(14_900),
            Money.wons(15_000),
        ).shouldForAll {
            shop.isValidOrderAmount(it).shouldBeFalse()
        }
    }

    "주문 금액이 최소금액과 동일하면 true 반환한다" {
        val minOrderAmount = Money.wons(10_000)
        val shop = ShopBuilder(minOrderAmount = minOrderAmount).build()

        shop.isValidOrderAmount(minOrderAmount).shouldBeTrue()
    }

    "주문 금액이 최소 금액보다 크면 true 반환한다" {
        val shop = ShopBuilder(minOrderAmount = Money.wons(15_900)).build()

        listOf(
            Money.wons(16_000),
            Money.wons(20_000),
            Money.wons(100_000),
        ).shouldForAll {
            shop.isValidOrderAmount(it).shouldBeTrue()
        }
    }

    "수수료 = (기존 수수료 + 요금(=금액의 n%))" {
        val shop = ShopBuilder(
            commission = Money.ZERO,
            commissionRate = Ratio.valueOf(0.1),
        ).build()

        shop.billCommissionFee(Money.wons(20_000))

        shop.commission shouldBe Money.wons(2_000)
    }
})
