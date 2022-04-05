package com.myshop.member.command.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Embeddable
public class MemberId implements Serializable {
    @Column(name = "member_id")
    private String id;

    public static MemberId of(String id) {
        return new MemberId(id);
    }
}
