package com.github.keunwon

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.core.generic.money.Ratio
import com.github.keunwon.food.order.service.Cart
import com.github.keunwon.food.order.service.CartLineItem
import com.github.keunwon.food.order.service.CartOption
import com.github.keunwon.food.order.service.CartOptionGroup
import com.github.keunwon.food.order.service.OrderService
import com.github.keunwon.food.shop.domain.Menu
import com.github.keunwon.food.shop.domain.MenuRepository
import com.github.keunwon.food.shop.domain.OptionGroupSpecification
import com.github.keunwon.food.shop.domain.OptionSpecification
import com.github.keunwon.food.shop.domain.Shop
import com.github.keunwon.food.shop.domain.ShopRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class FoodApplication(
    private val orderService: OrderService,
    private val shopRepository: ShopRepository,
    private val menuRepository: MenuRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val shop = Shop(
            name = "오겹돼지",
            open = true,
            minOrderAmount = Money.wons(13_000),
            commissionRate = Ratio.valueOf(0.1),
        )
        val menu = Menu(
            name = "삼겹살 1인 세트",
            description = "삽겹살 + 야채 + 김치찌개 + 공기밥 1개",
            shopId = 1L,
            basic = OptionGroupSpecification.basic(
                name = "기본",
                exclusive = true,
                optionSpecs = mutableListOf(
                    OptionSpecification("소(250)g", Money.wons(12_000)),
                    OptionSpecification("중(400)g", Money.wons(16_000)),
                    OptionSpecification("대(600)g", Money.wons(20_000)),
                ),
            ),
            OptionGroupSpecification.additive(
                name = "맛선택",
                exclusive = false,
                OptionSpecification("일반 맛", Money.ZERO),
                OptionSpecification("매운 맛", Money.wons(1_000)),
            ),
            OptionGroupSpecification.additive(
                name = "추가 선택",
                exclusive = false,
                OptionSpecification("부추재래기 추가", Money.wons(1_000)),
                OptionSpecification("야채세트 추가", Money.wons(1_000)),
                OptionSpecification("고기(100g) 추가", Money.wons(5_000)),
            ),
        )
        shopRepository.save(shop)
        menuRepository.save(menu)

        val cart = Cart(1L, 1L, listOf(
            CartLineItem(1L, "삼겹살 1인 세트", 2, listOf(
                CartOptionGroup("기본", listOf(
                    CartOption("중(400)g", Money.wons(16_000)),
                )),
            )),
        ))
        orderService.placeOrder(cart)
        orderService.payOrder(1L)
        orderService.deliverOrder(1L)
    }
}

fun main(args: Array<String>) {
    runApplication<FoodApplication>(*args)
}
