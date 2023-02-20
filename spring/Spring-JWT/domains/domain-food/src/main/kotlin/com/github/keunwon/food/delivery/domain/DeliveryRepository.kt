package com.github.keunwon.food.delivery.domain

import org.springframework.data.repository.CrudRepository

interface DeliveryRepository : CrudRepository<Delivery, Long> {
}
