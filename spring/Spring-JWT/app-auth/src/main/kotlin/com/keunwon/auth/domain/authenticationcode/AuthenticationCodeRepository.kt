package com.keunwon.auth.domain.authenticationcode

import org.springframework.data.repository.CrudRepository

fun AuthenticationCodeRepository.getLastByEmail(email: String): AuthenticationCode {
    return findFirstByEmailOrderByCreatedAtDesc(email)
        ?: throw IllegalArgumentException("인증코드가 존재하지 않습니다. 이메일: $email")
}

interface AuthenticationCodeRepository : CrudRepository<AuthenticationCode, Long> {
    fun findFirstByEmailOrderByCreatedAtDesc(email: String): AuthenticationCode?
}
