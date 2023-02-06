package com.keunwon.jwt.domain.user

import com.keunwon.jwt.common.UserRole
import com.keunwon.jwt.common.jpa.BaseEntity
import com.keunwon.jwt.common.jpa.convert.BooleanConverter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Column(name = "username", length = 20)
    val username: String? = null,

    @Column(name = "password", length = 255)
    var password: String? = null,

    @Column(name = "name", length = 10)
    var name: String,

    @Column(name = "nickname", length = 50)
    var nickname: String,

    @Column(name = "email", length = 30)
    var email: String? = null,

    @Column(name = "login_type", length = 10)
    @Enumerated(EnumType.STRING)
    val loginType: LoginType = LoginType.SIMPLE,

    @Column(name = "failed_password_count")
    var failedPasswordCount: Int = 0,

    @Column(name = "accountNonLocked", length = 1)
    @Convert(converter = BooleanConverter::class)
    var isAccountNonLocked: Boolean = true,

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    var role: UserRole,

    id: Long = 0,
) : BaseEntity(id) {
    fun matchPassword(password: String, passwordEncoder: PasswordEncoder): Boolean =
        passwordEncoder.matches(password, this.password)

    fun reset() {
        failedPasswordCount = 0
        isAccountNonLocked = true
    }

    fun incrementFailures() {
        failedPasswordCount++
        isAccountNonLocked = failedPasswordCount < MAX_PASSWORD_FAILURED_COUNT
    }

    companion object {
        const val MAX_PASSWORD_FAILURED_COUNT = 10
    }
}

enum class UserRole(val title: String) {
    ADMIN("관리자"),
    USER("사용자"),
    GUEST("게스트");

    companion object {
        val DEFAULT_ROLES = listOf(USER.name)
    }
}

fun generatedGrantedAuthorityList(vararg roles: UserRole): List<GrantedAuthority> =
    roles.map { SimpleGrantedAuthority(it.name) }.toList()

enum class LoginType(val title: String) {
    SIMPLE("기본 방식"),
    OAUTH2("소셜 로그인 방식");
}
