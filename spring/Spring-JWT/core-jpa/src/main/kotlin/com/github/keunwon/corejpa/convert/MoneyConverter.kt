package com.github.keunwon.corejpa.convert

import com.github.keunwon.core.generic.Money
import java.math.BigDecimal
import javax.persistence.AttributeConverter

class MoneyConverter : AttributeConverter<Money?, BigDecimal?> {
    override fun convertToDatabaseColumn(money: Money?): BigDecimal? {
        return money?.amount
    }

    override fun convertToEntityAttribute(dbData: BigDecimal?): Money? {
        return dbData?.let { Money.wons(it) }
    }
}
