package com.github.keunwon.user.memeber

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import java.time.LocalDateTime

class AccountPolicyTest : StringSpec({
    "로그인 최대 횟수를 초과하면 오류가 발생한다" {
        val accountPolicy = AccountPolicy(
            failedPasswordCount = AccountPolicy.MAX_FAILED_PASSWORD_COUNT + 1,
        )
        val now = LocalDateTime.of(2030, 1, 1, 0, 0)

        shouldThrowExactly<IllegalStateException> {
            accountPolicy.validateWith(now)
        }
    }

    "비밀번호 만료일자가 지난 경우 오류가 발생한다" {
        val accountPolicy = AccountPolicy(
            lastPasswordModifiedDateTime = LocalDateTime.of(2023, 1, 1, 0, 0),
        )
        val now = accountPolicy.lastPasswordModifiedDateTime.plusDays(90L)

        shouldThrowExactly<IllegalStateException> {
            accountPolicy.validateWith(now)
        }
    }
})
