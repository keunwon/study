package com.github.keunwon.food.shop.domain

import org.springframework.data.repository.CrudRepository

interface MenuRepository : CrudRepository<Menu, Long> {
    fun findByShopId(shopId: Long): List<Menu>
}
