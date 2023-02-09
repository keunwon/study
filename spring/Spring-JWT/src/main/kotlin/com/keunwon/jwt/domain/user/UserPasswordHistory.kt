package com.keunwon.jwt.domain.user

import com.keunwon.jwt.common.jpa.BaseEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class UserPasswordHistory(
    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "password")
    val password: Password,

    createdAt: LocalDateTime = LocalDateTime.now(),
    id: Long = 0L,
) : BaseEntity(id, createdAt) {
    constructor(user: User) : this(user.id, user.password!!)
}
