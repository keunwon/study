package com.keunwon.jwt.domain

import com.keunwon.jwt.common.jpa.BaseEntity
import com.keunwon.jwt.common.jpa.convert.BooleanConverter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Column(name = "username", length = 20)
    var username: String? = null,

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

    @Column(name = "fail_count")
    var failCount: Int = 0,

    @Column(name = "activated", length = 1)
    @Convert(converter = BooleanConverter::class)
    var isActivated: Boolean = true,

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    var role: UserRole,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) : BaseEntity()

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
