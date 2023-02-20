package com.github.keunwon.food.delivery.domain

import com.github.keunwon.corejpa.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "deliveries")
class Delivery(
    @Column(name = "order_id")
    val orderId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: com.github.keunwon.food.delivery.domain.DeliveryStatus,

    id: Long = 0L,
) : BaseEntity(id) {
    fun complete() {
        status = com.github.keunwon.food.delivery.domain.DeliveryStatus.DELIVERED
    }

    companion object {
        fun started(orderId: Long): com.github.keunwon.food.delivery.domain.Delivery {
            return com.github.keunwon.food.delivery.domain.Delivery(orderId,
                com.github.keunwon.food.delivery.domain.DeliveryStatus.DELIVERING)
        }
    }
}

enum class DeliveryStatus(title: String) {
    DELIVERING("배송 중"),
    DELIVERED("배송 완료");
}
