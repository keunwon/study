package com.github.keunwon.food.shop.domain

import com.github.keunwon.core.generic.money.Money
import com.github.keunwon.corejpa.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "menus")
class Menu(
    @Column(name = "food_name")
    val name: String,

    @Column(name = "food_description")
    val description: String,

    @Column(name = "shop_id")
    val shopId: Long,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "menu_id")
    val optionGroupSpecs: MutableList<OptionGroupSpecification>,

    id: Long = 0L,
) : BaseEntity(id) {
    private val basicOptionGroupSpec: OptionGroupSpecification
        get() = optionGroupSpecs.firstOrNull { it.basic } ?: throw IllegalStateException()

    constructor(
        name: String,
        description: String,
        shopId: Long,
        basic: OptionGroupSpecification,
        vararg groups: OptionGroupSpecification,
    ) : this(
        name,
        description,
        shopId,
        mutableListOf(basic, *groups)
    )

    fun basePrice(): Money {
        return basicOptionGroupSpec.optionSpecs[0].price
    }

    fun validateOrder(menuName: String, optionGroups: List<OptionGroup>) {
        require(name == menuName) { "기본 상품이 변경되었습니다." }
        require(isSatisfiedBy(optionGroups)) { "메뉴가 변경되었습니다." }
    }

    private fun isSatisfiedBy(cartOptionGroups: List<OptionGroup>): Boolean {
        return cartOptionGroups.any { group ->
            optionGroupSpecs.any { spec -> spec.isSatisfiedBy(group) }
        }
    }
}
