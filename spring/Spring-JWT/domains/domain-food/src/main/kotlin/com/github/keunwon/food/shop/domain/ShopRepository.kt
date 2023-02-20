package com.github.keunwon.food.shop.domain

import org.springframework.data.repository.CrudRepository

interface ShopRepository : CrudRepository<Shop, Long> {
}
