package com.github.keunwon.food.domain.delivery

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
    var status: DeliveryStatus,

    id: Long = 0L,
) : BaseEntity(id) {
    fun complete() {
        status = DeliveryStatus.DELIVERED
    }

    companion object {
        fun started(orderId: Long): Delivery {
            return Delivery(orderId, DeliveryStatus.DELIVERING)
        }
    }
}

enum class DeliveryStatus(title: String) {
    DELIVERING("배송 중"),
    DELIVERED("배송 완료");
}
