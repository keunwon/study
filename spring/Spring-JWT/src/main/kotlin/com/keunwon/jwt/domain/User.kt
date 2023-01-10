package com.keunwon.jwt.domain

import com.keunwon.jwt.common.jpa.convert.BooleanConverter
import com.keunwon.jwt.security.jwt.AbstractCustomUser
import com.keunwon.jwt.security.oauth.OAuth2Attributes
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Column(name = "username", length = 20)
    override var username: String = "",

    @Column(name = "password", length = 255)
    override var password: String = "",

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
    override var failCount: Int = 0,

    @Column(name = "activated", length = 1)
    @Convert(converter = BooleanConverter::class)
    override var isActivated: Boolean = true,

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    override var role: UserRole,
) : AbstractCustomUser<Long>() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null

    @CreatedDate
    var createdAt = LocalDateTime.now()

    @LastModifiedDate
    var updateAt = LocalDateTime.now()

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinColumn(name = "token_id")
    var token: UserToken? = null

    fun successLogin(userToken: UserToken) {
        this.token = this.token?.apply { updateToken(userToken) } ?: userToken
        this.failCount = 0
    }

    fun failLogin() {
        this.failCount++
        if (10 < failCount) isActivated = false
    }

    fun updateByOauthLogin(attributes: OAuth2Attributes) {
        this.name = attributes.name
        this.nickname = attributes.nickname
    }

    companion object {
        fun ofOAuth2(name: String, nickname: String, email: String) = User(
            name = name,
            nickname = nickname,
            email = email,
            loginType = LoginType.OAUTH,
            role = UserRole.USER,
        )
    }
}

enum class UserRole(val title: String) {
    ADMIN("관리자"),
    USER("사용자"),
    GUEST("게스트");
}

enum class LoginType(val title: String) {
    SIMPLE("기본 방식"),
    OAUTH("소셜 로그인 방식");
}

fun generatedGrantedAuthorityList(vararg roles: UserRole): List<GrantedAuthority> =
    roles.map { SimpleGrantedAuthority(it.name) }.toList()
