package com.github.keunwon.food.domain.order

import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long> {
}
