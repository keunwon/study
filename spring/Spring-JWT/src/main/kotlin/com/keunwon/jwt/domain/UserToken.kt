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

    @Column(name = "access_token", columnDefinition = "TEXT")
    var accessToken: String,

    @Column(name = "refresh_token", columnDefinition = "TEXT")
    var refreshToken: String,

    @Column(name = "expired_access_token")
    var expiredAccessToken: LocalDateTime,

    @Column(name = "expired_refresh_token")
    var expiredRefreshToken: LocalDateTime,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    val id: Long = 0,
) : BaseEntity() {

    fun updateToken(userToken: UserToken) {
        this.accessToken = userToken.accessToken
        this.refreshToken = userToken.refreshToken
        this.expiredAccessToken = userToken.expiredAccessToken
        this.expiredRefreshToken = userToken.expiredRefreshToken
    }
}
