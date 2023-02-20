package com.github.keunwon.food.domain.shop

import com.github.keunwon.core.generic.money.Money
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class MenuTest : StringSpec({
    "메뉴이름이 일치하지 않으면 오류가 발생한다" {
        val menu = MenuBuilder(name = "삼겹살").build()

        shouldThrowExactly<IllegalArgumentException> {
            menu.validateOrder(
                "오겹살",
                listOf(OptionGroupBuilder().build()),
            )
        }
    }

    "옵션 그룹 이름이 일치하지 않으면 오류가 발생한다" {
        val menu = MenuBuilder().build()

        shouldThrowExactly<IllegalArgumentException> {
            menu.validateOrder(
                menuName = "기본 메뉴",
                optionGroups = mutableListOf(OptionGroupBuilder().build()),
            )
        }
    }

    "옵션 이름이 일치하지 않으면 오류가 발생한다" {
        val menu = MenuBuilder().build()

        shouldThrowExactly<IllegalArgumentException> {
            menu.validateOrder(menuName = menu.name, listOf(
                OptionGroupBuilder(
                    options = listOf(
                        OptionBuilder(
                            name = "대 (600)g"
                        ).build()
                    )
                ).build(),
            ))
        }
    }

    "옵션 가격이 일치하지 않으면 오류가 발생한다" {
        val menu = MenuBuilder().build()

        shouldThrowExactly<IllegalArgumentException> {
            menu.validateOrder(
                menu.name,
                listOf(
                    OptionGroupBuilder(
                        options = listOf(
                            OptionBuilder(
                                price = Money.wons(15_000)
                            ).build(),
                        )
                    ).build(),
                )
            )
        }
    }
})
