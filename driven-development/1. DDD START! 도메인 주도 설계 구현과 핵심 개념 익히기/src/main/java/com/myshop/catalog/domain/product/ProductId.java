package com.myshop.catalog.domain.product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductId implements Serializable {

    @Column(name = "product_id")
    private String id;
}
