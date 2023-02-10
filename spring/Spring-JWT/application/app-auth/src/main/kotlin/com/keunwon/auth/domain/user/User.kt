package com.keunwon.auth.domain.user

import com.keunwon.auth.common.UserRole
import com.keunwon.auth.common.jpa.BaseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Embedded
    val information: UserInformation,

    @Embedded
    var password: Password? = null,

    @Embedded
    val loginPolicy: LoginPolicy = LoginPolicy(),

    @Column(name = "login_type", length = 10)
    @Enumerated(EnumType.STRING)
    val loginType: LoginType = LoginType.SIMPLE,

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    var role: UserRole,

    id: Long = 0,
) : BaseEntity(id) {
    fun authenticate(password: Password, passwordEncoder: PasswordEncoder) {
        identify(passwordEncoder.matches(password.value, this.password!!.value)) { "사용자 비밀번호가 일치하지 않습니다." }
    }

    fun changePassword(oldPassword: Password, newPassword: Password, passwordEncoder: PasswordEncoder) {
        identify(loginType == LoginType.SIMPLE) { "소셜 로그인 사용자입니다." }
        identify(!passwordEncoder.matches(oldPassword, newPassword)) { "기존 비밀번호와 일치합니다." }
        this.password = newPassword
    }

    private fun identify(value: Boolean, lazyMessage: () -> Any = {}) {
        if (!value) {
            val message = lazyMessage()
            throw UnIdentifiedUserException(message.toString())
        }
    }
}

enum class LoginType(val title: String) {
    SIMPLE("기본 방식"),
    OAUTH2("소셜 로그인 방식");
}
