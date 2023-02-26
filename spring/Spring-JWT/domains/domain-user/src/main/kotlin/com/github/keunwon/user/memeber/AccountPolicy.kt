package com.github.keunwon.user.memeber

import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * @property failedPasswordCount 비밀번호 실패 횟수
 * @property enabled             계정 활성화
 */
@Embeddable
class AccountPolicy(
    @Column
    var failedPasswordCount: Int = 0,

    @Column
    var enabled: Boolean = true,

    @Column
    var lastPasswordModifiedDateTime: LocalDateTime = LocalDateTime.now(),
) {
    private val isAccountLocked: Boolean
        get() = MAX_FAILED_PASSWORD_COUNT < failedPasswordCount

    private val passwordExpiryDateTime = lastPasswordModifiedDateTime + PASSWORD_EXPIRY_DAYS

    fun authenticateSuccess() {
        failedPasswordCount = 0
    }

    fun authenticateFailed() {
        failedPasswordCount++
    }

    fun modifiedPasswordBy(now: LocalDateTime) {
        lastPasswordModifiedDateTime = now
    }

    fun validateWith(now: LocalDateTime) {
        check(enabled) { "사용자 계정이 비활성화 되었습니다." }
        check(!isAccountLocked) {
            "로그인 최대 시도 횟수를 초과하였습니다. 최대 횟수: $MAX_FAILED_PASSWORD_COUNT"
        }
        check(now < passwordExpiryDateTime) {
            val datetimeToString = lastPasswordModifiedDateTime.let {
                "${it.year} 년 ${it.month} 월 ${it.dayOfMonth} 일"
            }
            "비밀번호 변경이 필요합니다. 마지막 변경: $datetimeToString"
        }
    }

    companion object {
        const val MAX_FAILED_PASSWORD_COUNT = 10
        private val PASSWORD_EXPIRY_DAYS = Duration.ofDays(90L)
    }
}
