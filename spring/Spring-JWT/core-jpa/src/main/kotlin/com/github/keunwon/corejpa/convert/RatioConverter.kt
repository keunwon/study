package com.github.keunwon.corejpa.convert

import com.github.keunwon.core.generic.money.Ratio
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class RatioConverter : AttributeConverter<Ratio, Double> {
    override fun convertToDatabaseColumn(ratio: Ratio): Double {
        return ratio.rate
    }

    override fun convertToEntityAttribute(dbData: Double): Ratio {
        return Ratio.valueOf(dbData)
    }
}
