package com.github.keunwon.food.order.domain

import com.github.keunwon.food.domain.shop.domain.Menu
import com.github.keunwon.food.domain.shop.domain.MenuRepository
import com.github.keunwon.food.domain.shop.domain.Shop
import com.github.keunwon.food.domain.shop.domain.ShopRepository
import org.springframework.stereotype.Component

@Component
class OrderValidator(
    private val shopRepository: ShopRepository,
    private val menuRepository: MenuRepository,
) {
    fun validate(order: Order) {
        validate(order, getShop(order), getMenu(order))
    }

    fun validate(order: Order, shop: Shop, menus: Map<Long, Menu>) {
        require(shop.open) { "가게가 영업중이 아닙니다." }
        check(order.orderLineItems.isNotEmpty()) { "주문 항목이 비어 있습니다." }
        check(shop.isValidOrderAmount(order.calculateTotalPrice())) {
            "최소 주문 금액 ${shop.minOrderAmount} 이상을 주문해주세요."
        }
        order.orderLineItems.forEach { item ->
            val menu = menus[item.menuId] ?: throw IllegalArgumentException("주문 메뉴가 존재하지 않습니다.")
            validateOrderLineItem(item, menu)
        }
    }

    private fun validateOrderLineItem(item: OrderLineItem, menu: Menu) {
        require(menu.name == item.name) { "기본 상품이 변경되었습니다." }
        item.groups.forEach { group -> validateOrderOptionGroup(group, menu) }
    }

    private fun validateOrderOptionGroup(group: OrderOptionGroup, menu: Menu) {
        menu.optionGroupSpecs.forEach { spec ->
            if (spec.isSatisfiedBy(group.convertToOptionGroup())) return
        }
        throw IllegalArgumentException("메뉴가 변경되었습니다.")
    }

    private fun getShop(order: Order): Shop {
        return shopRepository.getById(order.shopId)
    }

    private fun getMenu(order: Order): Map<Long, Menu> {
        return menuRepository.findAllById(order.menuIds).associateBy { it.id }
    }
}
