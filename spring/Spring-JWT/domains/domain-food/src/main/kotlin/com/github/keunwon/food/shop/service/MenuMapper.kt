package com.github.keunwon.food.shop.service

import com.github.keunwon.food.shop.domain.Menu
import com.github.keunwon.food.shop.domain.OptionGroupSpecification
import com.github.keunwon.food.shop.domain.OptionSpecification
import org.springframework.stereotype.Component

@Component
class MenuMapper {
    fun mapFrom(registration: MenuRegistration): Menu {
        return Menu(
            name = registration.name,
            description = registration.description,
            shopId = registration.shopId,
            optionGroupSpecs = registration.menuOptionGroups.map { toOptionGroupSpecification(it) }.toMutableList(),
        )
    }

    private fun toOptionGroupSpecification(menuOptionGroup: MenuOptionGroup): OptionGroupSpecification {
        return OptionGroupSpecification(
            name = menuOptionGroup.name,
            exclusive = menuOptionGroup.exclusive,
            basic = menuOptionGroup.basic,
            optionSpecs = menuOptionGroup.menuOptions.map { toOptionSpecification(it) }.toMutableList(),
        )
    }

    private fun toOptionSpecification(menuOption: MenuOption): OptionSpecification {
        return OptionSpecification(
            name = menuOption.name,
            price = menuOption.price,
        )
    }
}
