package com.github.keunwon.food.order.domain

import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long> {
}
