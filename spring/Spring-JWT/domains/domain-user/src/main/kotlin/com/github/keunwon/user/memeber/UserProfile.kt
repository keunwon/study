package com.github.keunwon.user.memeber

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserProfile(
    @Column(name = "email")
    val email: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "nickname")
    var nickname: String,
)
