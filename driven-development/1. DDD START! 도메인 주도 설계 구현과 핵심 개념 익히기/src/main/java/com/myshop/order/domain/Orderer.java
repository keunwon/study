package com.myshop.order.domain;

import com.myshop.member.domain.MemberId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Orderer {
    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "order_id"))
    )
    private MemberId id;

    @Column(name = "orderer_name")
    private String name;
}
