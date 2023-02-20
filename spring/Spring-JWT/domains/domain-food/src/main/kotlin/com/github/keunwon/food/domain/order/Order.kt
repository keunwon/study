package com.github.keunwon.food.domain.order

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.corejpa.BaseEntity
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "shop_id")
    val shopId: Long,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    val orderLineItems: MutableList<OrderLineItem>,

    @Column(name = "ordered_time")
    val orderedDateTime: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: OrderStatus = OrderStatus.NONE,

    id: Long = 0L,
) : BaseEntity(id) {
    val menuIds: List<Long>
        get() = orderLineItems.map { it.menuId }

    fun place(orderValidator: OrderValidator) {
        orderValidator.validate(this)
        ordered()
    }
   
    private fun ordered() {
        status = OrderStatus.ORDERED
    }

    fun payed() {
        status = OrderStatus.PAYED
    }

    fun delivered() {
        status = OrderStatus.DELIVERED
    }

    fun calculateTotalPrice(): Money {
        return Money.sum(orderLineItems) { it.calculatePrice() }
    }
}

enum class OrderStatus(title: String) {
    NONE("상태가 없음"),
    ORDERED("주문 완료"),
    PAYED("결제 완료"),
    DELIVERED("배송 완료");
}
