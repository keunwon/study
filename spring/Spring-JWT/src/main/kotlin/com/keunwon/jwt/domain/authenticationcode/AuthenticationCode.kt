package com.keunwon.jwt.domain.authenticationcode

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

    createdAt: LocalDateTime = LocalDateTime.now(),
    id: Long = 0,
) : BaseEntity(id, createdAt) {
    private val expiryDateTime: LocalDateTime
        get() = createdAt + EXPIRY_MINUTE_TIME

    fun authenticate(code: String) {
        require(this.code == code) { "인증코드가 일치하지 않습니다." }
        check(!authenticated) { "이미 인증되었습니다." }
        check(LocalDateTime.now() < expiryDateTime) { "인증코드가 만료되었습니다." }
        authenticated = true
    }

    fun validate(code: String) {
        require(this.code == code) { "인증코드가 일치하지 않습니다." }
        check(authenticated) { "인증코드가 인증되지 않았습니다." }
    }

    companion object {
        private val EXPIRY_MINUTE_TIME = Duration.ofMinutes(10L)
    }
}
