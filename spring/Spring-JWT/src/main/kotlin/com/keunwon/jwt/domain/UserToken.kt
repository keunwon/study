package com.keunwon.jwt.domain

import com.keunwon.jwt.common.jpa.BaseEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserToken(
    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "refresh_token")
    var refreshToken: String,

    @Column(name = "expired_refresh_token")
    var expiredRefreshToken: LocalDateTime,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    val id: Long = 0,
) : BaseEntity() {

    fun updateToken(userToken: UserToken) {
        this.refreshToken = userToken.refreshToken
        this.expiredRefreshToken = userToken.expiredRefreshToken
    }
}
