package com.myshop.catalog.command.domain.product.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Option {
    @Column(name = "option_value")
    private String value;

    @Column(name = "option_title")
    private String title;
}
