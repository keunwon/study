package com.github.keunwon.food.billing.domain

import org.springframework.data.repository.CrudRepository

interface BillingRepository : CrudRepository<Billing, Long> {
    fun findByShopId(shopId: Long): Billing?
}
