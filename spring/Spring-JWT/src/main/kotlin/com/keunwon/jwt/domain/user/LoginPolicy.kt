package com.keunwon.jwt.domain.user

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class LoginPolicy(
    @Column(name = "failed_password_count")
    var failedPasswordCount: Int = 0,
) {
    val isAccountNotLocked: Boolean
        get() = failedPasswordCount < MAX_PASSWORD_FAILURED_COUNT

    fun reset() {
        failedPasswordCount = 0
    }

    fun loginFailed() {
        failedPasswordCount++
    }

    companion object {
        const val MAX_PASSWORD_FAILURED_COUNT = 10
    }
}
