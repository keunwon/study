package com.github.keunwon.food.domain.shop

import org.springframework.data.repository.CrudRepository

interface MenuRepository : CrudRepository<Menu, Long> {
    fun findByShopId(shopId: Long): List<Menu>
}
