package com.keunwon.auth.common.jpa.convert

import javax.persistence.AttributeConverter

class BooleanConverter : AttributeConverter<Boolean, Char> {
    override fun convertToDatabaseColumn(attribute: Boolean?): Char =
        if (attribute != null && attribute) 'Y' else 'N'

    override fun convertToEntityAttribute(dbData: Char): Boolean = dbData == 'Y'
}
