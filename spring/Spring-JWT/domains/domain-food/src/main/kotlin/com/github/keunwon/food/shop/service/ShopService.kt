package com.github.keunwon.food.shop.service

import com.github.keunwon.food.domain.shop.domain.MenuRepository
import com.github.keunwon.food.domain.shop.domain.ShopRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShopService(
    private val shopRepository: ShopRepository,
    private val menuRepository: MenuRepository,
) {
    @Transactional(readOnly = true)
    fun getMenuBoard(shopId: Long): MenuBoard {
        val shop = shopRepository.getById(shopId)
        val menus = menuRepository.findByShopId(shop.id)
        return MenuBoard(shop, menus)
    }
}
