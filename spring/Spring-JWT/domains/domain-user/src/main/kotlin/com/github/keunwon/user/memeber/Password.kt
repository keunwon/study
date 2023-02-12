package com.github.keunwon.user.memeber

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Password(
    @Column(name = "password", length = 255)
    val value: String,
)
