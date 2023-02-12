package com.github.keunwon.user.authenticationcode

import org.springframework.data.annotation.CreatedDate
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class AuthenticationCode(
    @Column(nullable = false, length = 30)
    val email: String,

    @Column(nullable = false, length = 8)
    val code: String = UUID.randomUUID().toString().take(8),

    @Column(nullable = false)
    var authenticated: Boolean = false,

    @CreatedDate
    var createLocalDate: LocalDateTime = LocalDateTime.now(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
    private val expiryDateTime: LocalDateTime
        get() = createLocalDate + EXPIRY_MINUTE

    fun authenticate(code: String, dateTime: LocalDateTime) {
        require(this.code == code) { "인증코드가 일치하지 않습니다." }
        check(dateTime < expiryDateTime) { "인증코드가 만료되었습니다." }
        check(!authenticated) { "이미 인증되었습니다." }
        authenticated = true
    }

    fun validate(code: String) {
        require(this.code == code) { "인증코드가 일치하지 않습니다." }
        check(authenticated) { "인증코드가 인증되지 않았습니다." }
    }

    companion object {
        private val EXPIRY_MINUTE = Duration.ofMinutes(10L)
    }
}
