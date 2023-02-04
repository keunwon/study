package com.keunwon.jwt.domain

import com.keunwon.jwt.common.jpa.BaseEntity
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class AuthenticationCode(
    @Column(name = "email")
    val email: String,

    @Column(nullable = false, length = 8)
    val code: String = UUID.randomUUID().toString().take(8),

    @Column(nullable = false)
    var authenticated: Boolean = false,
) : BaseEntity() {
    private val expiryDateTime: LocalDateTime
        get() = createdAt + EXPIRY_MINUTE_TIME

    fun authenticate(code: String) {
        require(this.code == code) { "인증 코드가 일치하지 않습니다." }
        check(!authenticated) { "이미 인증되었습니다." }
        check(LocalDateTime.now() < expiryDateTime) { "인증코드가 만료되었습니다." }
        authenticated = true
    }

    companion object {
        private val EXPIRY_MINUTE_TIME = Duration.ofMinutes(10L)
    }
}
