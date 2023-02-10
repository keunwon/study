package com.keunwon.auth.domain.user

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserInformation(
    @Column(name = "email", length = 30)
    val email: String,

    @Column(name = "name", length = 10)
    var name: String,

    @Column(name = "nickname", length = 30)
    var nickname: String,
)
