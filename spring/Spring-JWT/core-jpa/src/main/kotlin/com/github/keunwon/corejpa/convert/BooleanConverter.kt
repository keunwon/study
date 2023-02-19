package com.github.keunwon.corejpa.convert

import javax.persistence.AttributeConverter

class BooleanConverter : AttributeConverter<Boolean, Char> {
    override fun convertToDatabaseColumn(attribute: Boolean): Char {
        return if (attribute) 'Y' else 'N'
    }

    override fun convertToEntityAttribute(dbData: Char): Boolean {
        return dbData == 'Y'
    }
}
