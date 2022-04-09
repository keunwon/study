package com.myshop.common.jpa.converter;

import com.myshop.common.model.Money;

import javax.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        return money == null ? null : money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new Money(dbData);
    }
}
