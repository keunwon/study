package com.github.keunwon.user.memeber

import com.github.keunwon.core.enums.LoginType
import com.github.keunwon.core.enums.UserRole
import com.github.keunwon.corejpa.BaseEntity
import java.time.LocalDateTime
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
    val profile: UserProfile,

    @Embedded
    var password: Password,

    @Embedded
    val accountPolicy: AccountPolicy = AccountPolicy(),

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type")
    val loginType: LoginType,

    @Enumerated(EnumType.STRING)
    val role: UserRole,

    id: Long = 0L,
) : BaseEntity(id) {
    init {
        if (loginType == LoginType.SIMPLE) {
            identify(password.value.isNotBlank()) { "비밀번호는 필수입니다." }
        } else {
            identify(password.value.isBlank()) { "잘못된 비밀번호 입력입니다." }
        }
    }

    fun authenticate(
        rawPassword: Password,
        userPasswordEncoder: UserPasswordEncoder,
        now: LocalDateTime,
    ) {
        accountPolicy.validateWith(now)
        if (!userPasswordEncoder.matches(rawPassword, this.password)) {
            accountPolicy.authenticateFailed()
            throw NotMatchUserPasswordException(profile.email, accountPolicy.failedPasswordCount)
        }
        accountPolicy.authenticateSuccess()
    }

    fun changePassword(
        oldRawPassword: Password,
        newRawPassword: Password,
        userPasswordEncoder: UserPasswordEncoder,
    ) {
        identify(oldRawPassword != newRawPassword) {
            "변경하려는 비밀번호와 현재 비밀번호와 동일합니다."
        }
        if (!userPasswordEncoder.matches(oldRawPassword, this.password)) {
            throw NotMatchUserPasswordException(profile.email, accountPolicy.failedPasswordCount)
        }
        password = userPasswordEncoder.encrypt(newRawPassword)
        accountPolicy.modifiedPasswordBy(LocalDateTime.now())
    }
}
