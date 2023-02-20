package com.github.keunwon.food.delivery

data class DeliveryBuilder(
    val orderId: Long = 1L,
    val status: com.github.keunwon.food.delivery.domain.DeliveryStatus = com.github.keunwon.food.delivery.domain.DeliveryStatus.DELIVERED,
    val id: Long = 1L,
) {
    fun build(): com.github.keunwon.food.delivery.domain.Delivery {
        return com.github.keunwon.food.delivery.domain.Delivery(
            orderId = orderId,
            status = status,
            id = id
        )
    }
}
