package com.myshop.catalog.command.domain.category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "value")
@Embeddable
@Access(AccessType.FIELD)
public class CategoryId implements Serializable {
    @Column(name = "category_id")
    private Long value;

    public static CategoryId of(Long value) {
        return new CategoryId(value);
    }
}
