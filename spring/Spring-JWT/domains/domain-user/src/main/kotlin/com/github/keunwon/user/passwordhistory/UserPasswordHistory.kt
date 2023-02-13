package com.github.keunwon.user.passwordhistory

import com.github.keunwon.corejpa.BaseEntity
import com.github.keunwon.user.memeber.Password
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class UserPasswordHistory(
    @Column(name = "users_id")
    val userId: Long,

    @Column(name = "password")
    val password: Password,

    createdDateTime: LocalDateTime = LocalDateTime.now(),
    id: Long = 0L,
) : BaseEntity(id, createdDateTime)
