package com.github.keunwon.food.shop

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.core.generic.money.Ratio
import com.github.keunwon.food.domain.shop.domain.Menu
import com.github.keunwon.food.domain.shop.domain.Option
import com.github.keunwon.food.domain.shop.domain.OptionGroup
import com.github.keunwon.food.domain.shop.domain.OptionGroupSpecification
import com.github.keunwon.food.domain.shop.domain.OptionSpecification
import com.github.keunwon.food.domain.shop.domain.Shop

data class MenuBuilder(
    val name: String = "삼겹살 1인 세트",
    val description: String = "삼겹살 + 야채 세트 + 김치찌개",
    val shopId: Long = 1L,
    val optionGroupSpecs: MutableList<OptionGroupSpecification> = mutableListOf(
        OptionGroupSpecificationBuilder().build(),
    ),
    val id: Long = 1L,
) {
    fun build(): Menu {
        return Menu(
            name = name,
            description = description,
            shopId = shopId,
            optionGroupSpecs = optionGroupSpecs,
            id = id,
        )
    }
}

data class ShopBuilder(
    val name: String = "오겹돼지",
    val open: Boolean = true,
    val minOrderAmount: Money = Money.wons(13000L),
    val commissionRate: Ratio = Ratio.valueOf(0.1),
    val commission: Money = Money.ZERO,
    val id: Long = 1L,
) {
    fun build(): Shop {
        return Shop(
            name = name,
            open = open,
            minOrderAmount = minOrderAmount,
            commissionRate = commissionRate,
            commission = commission,
            id = id,
        )
    }
}

data class OptionGroupSpecificationBuilder(
    val name: String = "기본",
    val exclusive: Boolean = true,
    val basic: Boolean = true,
    val optionSpecs: MutableList<OptionSpecification> = mutableListOf(
        OptionSpecificationBuilder().build()
    ),
    val id: Long = 1L,
) {
    fun build(): OptionGroupSpecification {
        return OptionGroupSpecification(
            name = name,
            exclusive = exclusive,
            basic = basic,
            optionSpecs = optionSpecs,
            id = id,
        )
    }
}

data class OptionSpecificationBuilder(
    val name: String = "소 (250)g",
    val price: Money = Money.wons(12_000),
    val id: Long = 1L,
) {
    fun build(): OptionSpecification {
        return OptionSpecification(
            name = name,
            price = price,
            id = id,
        )
    }
}

data class OptionGroupBuilder(
    val name: String = "기본",
    val options: List<Option> = listOf(
        OptionBuilder().build(),
    ),
) {
    fun build(): OptionGroup {
        return OptionGroup(
            name = name,
            options = options,
        )
    }
}

data class OptionBuilder(
    val name: String = "소 (250)g",
    val price: Money = Money.wons(12_000),
) {
    fun build(): Option {
        return Option(
            name = name,
            price = price,
        )
    }
}
