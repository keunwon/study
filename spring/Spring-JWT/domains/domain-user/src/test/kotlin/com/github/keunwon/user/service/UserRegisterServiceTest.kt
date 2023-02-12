package com.github.keunwon.user.service

import com.github.keunwon.user.AUTHENTICATION_VALID_CODE_CODE
import com.github.keunwon.user.AUTHENTICATION_WRONG_CODE_CODE
import com.github.keunwon.user.AuthenticationCodeBuilder
import com.github.keunwon.user.InmemoryAuthenticationCodeRepository
import com.github.keunwon.user.InmemoryUserRepository
import com.github.keunwon.user.USER_EMAIL
import com.github.keunwon.user.USER_NAME
import com.github.keunwon.user.USER_NICKNAME
import com.github.keunwon.user.USER_PASSWORD
import com.github.keunwon.user.UserBuilder
import com.github.keunwon.user.memeber.getByEmail
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.kotest.matchers.throwable.shouldHaveMessage
import java.time.LocalDateTime

class UserRegisterServiceTest : BehaviorSpec({
    val userRepository = InmemoryUserRepository()
    val authenticationCodeRepository = InmemoryAuthenticationCodeRepository()
    val userRegisterService = UserRegisterService(userRepository, authenticationCodeRepository)

    Given("가입된 이메일로 인증코드를 생성하면") {
        val user = UserBuilder().build()
        userRepository.save(user)

        Then("오류가 발생한다") {
            shouldThrowExactly<IllegalArgumentException> {
                userRegisterService.generateAuthenticationCode(user.profile.email)
            }
        }
    }

    Given("가입되지 않은 이메일로 인증코드를 생성하면") {
        val email = "wrong@test.com"

        Then("인증코드가 생성된다") {
            val code = userRegisterService.generateAuthenticationCode(email)
            code.shouldNotBeBlank()
        }
    }

    Given("인증코드를 발급받지 않은 이메일로 인증코드를 검증하면") {
        val wrongEmail = "wrong@test.com"
        val authenticationCode = AuthenticationCodeBuilder().build()
        authenticationCodeRepository.save(authenticationCode)

        Then("오류가 발생한다") {
            shouldThrowExactly<IllegalArgumentException> {
                userRegisterService.authenticateEmail(wrongEmail, authenticationCode.code)
            }
        }
    }

    Given("제한 시간 내에  인증코드를 생성하고") {
        val authenticationCode = AuthenticationCodeBuilder(
            createLocalDate = LocalDateTime.of(2030, 12, 31, 0, 0),
        ).build()
        val email = authenticationCode.email
        authenticationCodeRepository.save(authenticationCode)

        When("성공 인증코드로 검증하면") {
            val code = authenticationCode.code

            Then("검증 성공한다") {
                userRegisterService.authenticateEmail(email, code)
            }
        }

        When("실패 인증코드로 검증하면") {
            val code = AUTHENTICATION_WRONG_CODE_CODE

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    userRegisterService.authenticateEmail(email, code)
                }.shouldHaveMessage(NOT_MATCH_CODE_ERROR_MESSAGE)
            }
        }
    }

    Given("인증코드 검증이 완료된 상태에서") {
        val authenticationCode = AuthenticationCodeBuilder(
            authenticated = true,
        ).build()
        authenticationCodeRepository.save(authenticationCode)

        Then("다시 검증을 하면 오류가 발생한다") {
            shouldThrowExactly<IllegalStateException> {
                userRegisterService.authenticateEmail(authenticationCode.email, authenticationCode.code)
            }.shouldHaveMessage("이미 인증되었습니다.")
        }
    }

    Given("제한 시간이 초과한 인증 코드를 생성하고") {
        val createdDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0)
        val authenticationCode = AuthenticationCodeBuilder(
            createLocalDate = createdDateTime,
        ).build()
        val email = authenticationCode.email
        authenticationCodeRepository.save(authenticationCode)

        When("이메일을 기준으로 성공 인증코드로 검증하면") {
            val code = authenticationCode.code

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalStateException> {
                    userRegisterService.authenticateEmail(email, code)
                }.shouldHaveMessage(EXPIRED_CODE_ERROR_MESSAGE)
            }
        }

        When("이메일을 기준으로 실패 인증코드로 검증하면") {
            val code = AUTHENTICATION_WRONG_CODE_CODE

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    userRegisterService.authenticateEmail(email, code)
                }.shouldHaveMessage(NOT_MATCH_CODE_ERROR_MESSAGE)
            }
        }
    }

    Given("인증코드 인증이 완료 상태에서") {
        val authenticationCode = AuthenticationCodeBuilder(
            authenticated = true,
        ).build()
        authenticationCodeRepository.save(authenticationCode)

        When("신규 이메일로 회원가입을 하면") {
            val userRegister = defaultUserRegister.copy()
            userRegisterService.register(userRegister)

            Then("회원이 생성된다") {
                val user = userRepository.getByEmail(authenticationCode.email)
                assertSoftly(user.profile) {
                    email shouldBe userRegister.email
                    name shouldBe userRegister.name
                    nickname shouldBe userRegister.nickname
                }
            }
        }

        When("1차, 2차 비밀번호가 일치하지 않은 상태로 회원가입을 하면") {
            val userRegister = defaultUserRegister.copy(
                password = "PASSWORD",
                confirmPassword = "NOT_MATCHED",
            )

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    userRegisterService.register(userRegister)
                }
            }
        }

        When("이미 가입된 이메일로 회원가입을 하면") {
            userRepository.save(UserBuilder().build())

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalStateException> {
                    userRegisterService.register(defaultUserRegister)
                }
            }
        }

        When("실패 인증코드로 회원가입을 하면") {
            val userRegister = defaultUserRegister.copy(
                code = AUTHENTICATION_WRONG_CODE_CODE,
            )

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    userRegisterService.register(userRegister)
                }
            }
        }
    }

    Given("인증코드 인증이 완료되지 않은 상태에서") {
        val authenticationCode = AuthenticationCodeBuilder().build()
        val userRegister = defaultUserRegister.copy()
        authenticationCodeRepository.save(authenticationCode)

        Then("회원가입을 하면 오류가 발생한다") {
            shouldThrowExactly<IllegalStateException> {
                userRegisterService.register(userRegister)
            }
        }
    }

    Given("인증코드를 생성하지 않고") {
        val userRegister = defaultUserRegister.copy()

        Then("회원가입을 하면 오류가 발생한다") {
            shouldThrowExactly<IllegalArgumentException> {
                userRegisterService.register(userRegister)
            }
        }
    }
}) {
    companion object {
        private const val EXPIRED_CODE_ERROR_MESSAGE = "인증코드가 만료되었습니다."
        private const val NOT_MATCH_CODE_ERROR_MESSAGE = "인증코드가 일치하지 않습니다."

        private val defaultUserRegister = UserRegister(
            email = USER_EMAIL,
            name = USER_NAME,
            nickname = USER_NICKNAME,
            password = USER_PASSWORD,
            confirmPassword = USER_PASSWORD,
            code = AUTHENTICATION_VALID_CODE_CODE,
        )
    }
}
