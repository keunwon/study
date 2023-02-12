package com.github.keunwon.user.authenticationcode

import com.github.keunwon.user.AUTHENTICATION_VALID_CODE_CODE
import com.github.keunwon.user.AUTHENTICATION_WRONG_CODE_CODE
import com.github.keunwon.user.AuthenticationCodeBuilder
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDateTime

class AuthenticationCodeTest : BehaviorSpec({
    Given("인증이 완료되지 않은 상태에서") {
        val createdDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0)
        val unExpiredDateTime = createdDateTime.plusMinutes(5L)
        val expiredDateTime = createdDateTime.plusMinutes(10)

        val authenticationCode = AuthenticationCodeBuilder(
            createLocalDate = createdDateTime,
        ).build()

        When("실패 인증코드로 검증을 하면") {
            val code = AUTHENTICATION_WRONG_CODE_CODE

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    authenticationCode.validate(code)
                }
            }
        }

        When("성공 인증코드로 검증을 하면") {
            val code = AUTHENTICATION_VALID_CODE_CODE

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalStateException> {
                    authenticationCode.validate(code)
                }
            }
        }

        When("제한 시간 초과 후, 성공 인증코드 인증을 하면") {
            val code = AUTHENTICATION_WRONG_CODE_CODE

            Then("인증코드 실패 오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    authenticationCode.authenticate(code, expiredDateTime)
                }.shouldHaveMessage("인증코드가 일치하지 않습니다.")
            }
        }

        When("제한 시간 초과 후, 실패 인증코드로 인증을 하면") {
            val code = AUTHENTICATION_VALID_CODE_CODE

            Then("제한 시간 초과 오류가 발생한다") {
                shouldThrowExactly<IllegalStateException> {
                    authenticationCode.authenticate(code, expiredDateTime)
                }.shouldHaveMessage("인증코드가 만료되었습니다.")
            }
        }

        When("제한 시간 내에 실패 인증코드로 인증을 하면") {
            val code = AUTHENTICATION_WRONG_CODE_CODE

            Then("인증코드가 일치하지 않아 오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    authenticationCode.authenticate(code, unExpiredDateTime)
                }.shouldHaveMessage("인증코드가 일치하지 않습니다.")
            }
        }

        When("제한 시간 내에 성공 인증코드 인증을 하면") {
            val code = AUTHENTICATION_VALID_CODE_CODE

            Then("인증이 완료된다") {
                assertDoesNotThrow {
                    authenticationCode.authenticate(code, unExpiredDateTime)
                    authenticationCode.validate(code)
                }
                authenticationCode.authenticated.shouldBeTrue()
            }
        }
    }

    Given("인증이 완료된 상태에서") {
        val createdDateTime = LocalDateTime.of(2023, 1, 1, 0, 0)
        val authenticationCode = AuthenticationCodeBuilder(
            authenticated = true,
            createLocalDate = createdDateTime,
        ).build()

        When("유효하지 않은 인증코드로 검증을 하면") {
            val code = AUTHENTICATION_WRONG_CODE_CODE

            Then("인증코드가 일치하지 않아 오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    authenticationCode.validate(code)
                }
            }
        }

        When("성공 인증코드로 검증하면") {
            val code = AUTHENTICATION_VALID_CODE_CODE

            Then("검증이 성공한다") {
                shouldNotThrowAny {
                    authenticationCode.validate(code)
                }
            }
        }

        When("성공 인증코드로 인증을 하면") {
            val code = AUTHENTICATION_VALID_CODE_CODE
            val datetime = createdDateTime.plusMinutes(5L)

            Then("이미 인증되어 오류가 발생한다") {
                shouldThrowExactly<IllegalStateException> {
                    authenticationCode.authenticate(code, datetime)
                }
            }
        }
    }
})
