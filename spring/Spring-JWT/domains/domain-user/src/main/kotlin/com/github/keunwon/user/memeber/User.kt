package com.github.keunwon.user.memeber

import com.github.keunwon.core.enums.LoginType
import com.github.keunwon.core.enums.UserRole
import javax.persistence.Column
import javax.persistence.Embedded
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
    @Embedded
    val profile: UserProfile,

    @Embedded
    var password: Password,

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type")
    val loginType: LoginType,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0L,
) {
    init {
        if (loginType == LoginType.SIMPLE) {
            identify(password.value.isNotBlank()) { "비밀번호는 필수입니다." }
        } else {
            identify(password.value.isBlank()) { "잘못된 비밀번호 입력입니다." }
        }
    }

    fun changePassword(oldRawPassword: Password, newRawPassword: Password, passwordEncrypt: PasswordEncrypt) {
        identify(passwordEncrypt.matches(oldRawPassword, this.password)) {
            "현재 비밀번호가 일치하지 않습니다."
        }
        identify(oldRawPassword != newRawPassword) {
            "변경하려는 비밀번호와 현재 비밀번호와 동일합니다."
        }
        this.password = passwordEncrypt.encrypt(newRawPassword)
    }
}
