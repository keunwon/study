package com.github.keunwon.corejpa.convert

import com.github.keunwon.core.generic.money.Money
import java.math.BigDecimal
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class MoneyConverter : AttributeConverter<Money, BigDecimal> {
    override fun convertToDatabaseColumn(money: Money): BigDecimal = money.amount

    override fun convertToEntityAttribute(dbData: BigDecimal): Money = Money.wons(dbData)
}
