package com.keunwon.auth

import com.keunwon.auth.domain.authenticationcode.AuthenticationCode
import com.keunwon.auth.domain.authenticationcode.AuthenticationCodeRepository
import java.util.*

class InmemoryAuthenticationCodeRepository : InmemoryRepository<AuthenticationCode>(), AuthenticationCodeRepository {
    override fun findById(id: Long): Optional<AuthenticationCode> = super.findById(id)

    override fun existsById(id: Long): Boolean = super.existsById(id)

    override fun deleteById(id: Long) = super.deleteById(id)

    override fun findFirstByEmailOrderByCreatedAtDesc(email: String): AuthenticationCode? {
        return findAll().filter { it.email == email }.maxByOrNull { it.createdAt }
    }
}
