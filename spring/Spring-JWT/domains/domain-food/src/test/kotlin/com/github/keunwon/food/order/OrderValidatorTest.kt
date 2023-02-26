package com.github.keunwon.food.order

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.food.order.domain.OrderValidator
import com.github.keunwon.food.shop.MenuBuilder
import com.github.keunwon.food.shop.OptionGroupSpecificationBuilder
import com.github.keunwon.food.shop.OptionSpecificationBuilder
import com.github.keunwon.food.shop.ShopBuilder
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class OrderValidatorTest : StringSpec({
    val orderValidator = OrderValidator(mockk(), mockk())

    "가게 오픈 전이면 오류가 발생한다" {
        val shop = ShopBuilder(
            open = false,
        ).build()

        shouldThrowExactly<IllegalArgumentException> {
            orderValidator.validate(OrderBuilder().build(), shop, emptyMap())
        }
    }

    "최소 금액 미만이면 오류가 발생한다" {
        val shop = ShopBuilder(
            minOrderAmount = Money.wons(20_000),
        ).build()

        shouldThrowExactly<IllegalStateException> {
            orderValidator.validate(OrderBuilder().build(), shop, emptyMap())
        }
    }

    "주문 목록이 비어있으면 오류가 발생한다" {
        val order = OrderBuilder(
            orderLineItems = mutableListOf(),
        ).build()

        shouldThrowExactly<IllegalStateException> {
            orderValidator.validate(
                order,
                ShopBuilder().build(),
                emptyMap(),
            )
        }
    }

    "메뉴옵션 그룹이 다르면 오류가 발생한다" {
        val menu = MenuBuilder(
            optionGroupSpecs = mutableListOf(
                OptionGroupSpecificationBuilder(name = "기본-2").build(),
            ),
            id = 1L,
        ).build()

        shouldThrowExactly<IllegalArgumentException> {
            orderValidator.validate(
                OrderBuilder().build(),
                ShopBuilder().build(),
                mapOf(menu.id to menu),
            )
        }
    }

    "메뉴옵션이 다르면 오류가 발생한다" {
        val menu = MenuBuilder(
            optionGroupSpecs = mutableListOf(
                OptionGroupSpecificationBuilder(
                    optionSpecs = mutableListOf(
                        OptionSpecificationBuilder(name = "대 (250)g").build(),
                    ),
                ).build(),
            ),
            id = 1L,
        ).build()

        shouldThrowExactly<IllegalArgumentException> {
            orderValidator.validate(
                OrderBuilder().build(),
                ShopBuilder().build(),
                mapOf(menu.id to menu),
            )
        }
    }
})
