package com.keunwon.jwt.domain

import com.keunwon.jwt.common.jpa.convert.BooleanConverter
import com.keunwon.jwt.jwt.AbstractCustomUser
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
class User (
    @Column(name = "username", length = 20, unique = true)
    override var username: String,

    @Column(name = "password", length = 255)
    override var password: String,

    @Column(name = "nickname", length = 50)
    var nickname: String,

    @Column(name = "fail_count")
    override var failCount: Int = 0,

    @Column(name = "activated", length = 1)
    @Convert(converter = BooleanConverter::class)
    override var isActivated: Boolean = false,

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    override var role: UserRole,
) : AbstractCustomUser<Long>() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
}

enum class UserRole(val title: String) {
    ADMIN("관리자"),
    USER("사용자"),
    GUEST("게스트");
}

fun generatedGrantedAuthorityList(vararg roles: UserRole): List<GrantedAuthority> =
    roles.map { SimpleGrantedAuthority(it.name) }.toList()
