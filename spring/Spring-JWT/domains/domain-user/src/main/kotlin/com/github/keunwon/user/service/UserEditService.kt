package com.github.keunwon.user.service

import com.github.keunwon.user.memeber.UserPasswordEncoder
import com.github.keunwon.user.memeber.UserRepository
import com.github.keunwon.user.memeber.getByEmail
import com.github.keunwon.user.passwordhistory.UserPasswordHistory
import com.github.keunwon.user.passwordhistory.UserPasswordHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserEditService(
    private val userRepository: UserRepository,
    private val userPasswordHistoryRepository: UserPasswordHistoryRepository,
    private val userPasswordEncoder: UserPasswordEncoder,
) {
    fun changePassword(passwordEdit: PasswordEdit) {
        val user = userRepository.getByEmail(passwordEdit.email)
        val usedPassword = userPasswordHistoryRepository
            .getAllByUserId(user.id)
            .any { userPasswordEncoder.matches(passwordEdit.newPassword, it.password) }
        check(!usedPassword) { "이미 사용했던 비밀번호입니다." }
        user.changePassword(passwordEdit.password, passwordEdit.newPassword, userPasswordEncoder)
        userPasswordHistoryRepository.save(UserPasswordHistory(user.id, user.password))
    }
}
