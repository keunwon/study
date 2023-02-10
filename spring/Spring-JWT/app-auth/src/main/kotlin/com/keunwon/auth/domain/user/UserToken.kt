package com.keunwon.auth.domain.user

import com.keunwon.auth.common.jpa.BaseEntity
import com.keunwon.auth.common.util.toLocalDateTime
import com.keunwon.auth.security.jwt.JwtRefreshToken
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class UserToken(
    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "refresh_token")
    var refreshToken: String,

    @Column(name = "expiration_refresh_token")
    var expirationRefreshToken: LocalDateTime,

    id: Long = 0,
) : BaseEntity(id) {
    constructor(userId: Long, jwtRefreshToken: JwtRefreshToken) : this(
        userId,
        jwtRefreshToken.value,
        jwtRefreshToken.expiredAt.toLocalDateTime(),
    )

    fun updateRefreshToken(jwtRefreshToken: JwtRefreshToken) {
        refreshToken = jwtRefreshToken.value
        expirationRefreshToken = jwtRefreshToken.expiredAt.toLocalDateTime()
    }
}
