package com.github.keunwon.userauth.usertoken

import com.github.keunwon.corejpa.BaseEntity
import com.github.keunwon.userauth.jwt.RefreshToken
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class UserToken(
    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "refresh_token")
    var refreshToken: String,

    @Column(name = "expiry_at")
    var expiryDateTime: LocalDateTime,

    id: Long = 0L,
) : BaseEntity(id) {
    constructor(userId: Long, refreshToken: RefreshToken) : this(
        userId,
        refreshToken.value,
        refreshToken.expirationDateTime()
    )

    fun update(refreshToken: RefreshToken) {
        this.refreshToken = refreshToken.value
        this.expiryDateTime = refreshToken.expirationDateTime()
    }
}
