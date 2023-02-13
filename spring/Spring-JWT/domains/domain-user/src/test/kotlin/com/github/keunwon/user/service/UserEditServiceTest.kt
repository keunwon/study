package com.github.keunwon.user.service

import com.github.keunwon.user.InmemoryUserPasswordHistoryRepository
import com.github.keunwon.user.InmemoryUserRepository
import com.github.keunwon.user.UserBuilder
import com.github.keunwon.user.UserPasswordHistoryBuilder
import com.github.keunwon.user.memeber.NoPasswordEncrypt
import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.UnIdentifiedUserException
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import java.time.LocalDateTime

class UserEditServiceTest : BehaviorSpec({
    val userRepository = InmemoryUserRepository()
    val userPasswordHistoryRepository = InmemoryUserPasswordHistoryRepository()
    val passwordEncrypt = NoPasswordEncrypt()
    val userEditService = UserEditService(userRepository, userPasswordHistoryRepository, passwordEncrypt)

    Given("비밀번호 이력이 존재하는 사용자가") {
        val user = UserBuilder().build()
        userRepository.save(user)
        val passwordHistory = mutableListOf(
            UserPasswordHistoryBuilder(
                userId = user.id,
                password = Password("OLD_PASSWORD1"),
                createdDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0),
            ).build(),
            UserPasswordHistoryBuilder(
                userId = user.id,
                password = Password("OLD_PASSWORD2"),
                createdDateTime = LocalDateTime.of(2023, 2, 1, 0, 0, 0),
            ).build(),
            UserPasswordHistoryBuilder(
                userId = user.id,
                password = Password("OLD_PASSWORD3"),
                createdDateTime = LocalDateTime.of(2023, 3, 1, 0, 0, 0)
            ).build(),
        )
        userPasswordHistoryRepository.saveAll(passwordHistory)

        When("이미 사용했던 비밀번호로 변경을 하면") {
            val oldPassword = passwordHistory[0].password
            val passwordEdit = PasswordEdit(user.profile.email, user.password, oldPassword)

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalStateException> {
                    userEditService.changePassword(passwordEdit)
                }
            }
        }

        When("기존 비밀번호로 동일하게 변경을 하면") {
            val passwordEdit = PasswordEdit(user.profile.email, user.password, user.password)

            Then("오류가 발생한다") {
                shouldThrowExactly<UnIdentifiedUserException> {
                    userEditService.changePassword(passwordEdit)
                }
            }
        }

        When("사용한적이 없는 비밀번호로 변경하면") {
            val newPassword = Password("NEW_PASSWORD")
            userEditService.changePassword(
                PasswordEdit(user.profile.email, user.password, newPassword)
            )

            Then("사용자 비밀번호가 변경되고, 비밀번호 변경 이력이 저장된다") {
                assertSoftly {
                    passwordEncrypt.matches(newPassword, user.password).shouldBeTrue()
                    with(userPasswordHistoryRepository.getAllByUserId(user.id)) {
                        shouldHaveSize(passwordHistory.size + 1)
                    }
                }
            }
        }
    }
})
