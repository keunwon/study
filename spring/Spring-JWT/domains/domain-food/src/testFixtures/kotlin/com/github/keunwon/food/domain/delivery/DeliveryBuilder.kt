package com.github.keunwon.food.domain.delivery

data class DeliveryBuilder(
    val orderId: Long = 1L,
    val status: DeliveryStatus = DeliveryStatus.DELIVERED,
    val id: Long = 1L,
) {
    fun build(): Delivery {
        return Delivery(
            orderId = orderId,
            status = status,
            id = id
        )
    }
}
