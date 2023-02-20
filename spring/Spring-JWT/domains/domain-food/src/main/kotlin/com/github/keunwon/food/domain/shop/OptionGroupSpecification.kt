package com.github.keunwon.food.domain.shop

import com.github.keunwon.corejpa.BaseEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

class OptionGroupSpecification(
    @Column(name = "name")
    val name: String,

    @Column(name = "exclusive")
    val exclusive: Boolean,

    @Column(name = "basic")
    val basic: Boolean,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    val optionSpecs: MutableList<OptionSpecification>,

    id: Long = 0L,
) : BaseEntity(id) {
    fun isSatisfiedBy(optionGroup: OptionGroup): Boolean {
        return isSatisfied(optionGroup.name, satisfied(optionGroup.options))
    }

    private fun isSatisfied(groupName: String, options: List<Option>): Boolean {
        if (name != groupName) return false
        if (options.isEmpty()) return false
        if (exclusive && options.size > 1) return false
        return true
    }

    private fun satisfied(options: List<Option>): List<Option> {
        return optionSpecs.flatMap { optionSpec ->
            options.filter { option -> optionSpec.isSatisfiedBy(option) }
        }
    }

    companion object {
        fun basic(
            name: String,
            exclusive: Boolean,
            optionSpecs: MutableList<OptionSpecification>,
        ): OptionGroupSpecification {
            return OptionGroupSpecification(name, exclusive, true, optionSpecs)
        }

        fun additive(
            name: String,
            exclusive: Boolean,
            vararg optionSpecs: OptionSpecification,
        ): OptionGroupSpecification {
            return OptionGroupSpecification(name, exclusive, false, optionSpecs.toMutableList())
        }
    }
}
