package com.github.keunwon.food.domain.delivery

import org.springframework.data.repository.CrudRepository

interface DeliveryRepository : CrudRepository<Delivery, Long> {
}
