package com.github.keunwon.user.service

import com.github.keunwon.user.AccountPolicyBuilder
import com.github.keunwon.user.InmemoryUserRepository
import com.github.keunwon.user.UserBuilder
import com.github.keunwon.user.WRONG_USER_PASSWORD
import com.github.keunwon.user.memeber.NotMatchUserPasswordException
import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.UserPasswordNoEncoder
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import java.time.LocalDateTime

class UserAuthenticationServiceTest : BehaviorSpec({
    val userRepository = InmemoryUserRepository()
    val passwordEncrypt = UserPasswordNoEncoder()
    val userAuthenticationService = UserAuthenticationService(userRepository, passwordEncrypt)

    Given("사용자를 생성하고") {
        val user = UserBuilder().build()
        userRepository.save(user)

        Then("사용자를 인증하면 성공한다") {
            val email = user.profile.email
            val password = user.password
            userAuthenticationService.authenticate(email, password)
        }

        Then("비밀번호가 일치하지 않으면 오류가 발생한다") {
            val email = user.profile.email
            val password = Password(WRONG_USER_PASSWORD)

            shouldThrowExactly<NotMatchUserPasswordException> {
                userAuthenticationService.authenticate(email, password).getOrThrow()
            }
        }
    }

    Given("사용자가 로그인 최대 시도 횟수를 최과한 경우") {
        val accountPolicy = AccountPolicyBuilder(
            failedPasswordCount = 11,
        ).build()
        val user = UserBuilder(
            accountPolicy = accountPolicy,
        ).build()
        userRepository.save(user)

        Then("사용자를 인증하면 오류가 발생한다") {
            val email = user.profile.email
            val password = user.password

            shouldThrowExactly<IllegalStateException> {
                userAuthenticationService.authenticate(email, password).getOrThrow()
            }
        }
    }

    Given("사용자 비밀번호가 만료되있는 상태에서") {
        val user = UserBuilder(
            accountPolicy = AccountPolicyBuilder(
                lastPasswordModifiedDateTime = LocalDateTime.of(2023, 1, 1, 0, 0),
            ).build(),
        ).build()
        userRepository.save(user)

        Then("사용자를 인증하면 오류가 발생한다") {
            val email = user.profile.email
            val password = user.password
            val now = user.accountPolicy.lastPasswordModifiedDateTime.plusDays(90L)

            shouldThrowExactly<IllegalStateException> {
                userAuthenticationService.authenticate(email, password, now).getOrThrow()
            }
        }
    }
})

