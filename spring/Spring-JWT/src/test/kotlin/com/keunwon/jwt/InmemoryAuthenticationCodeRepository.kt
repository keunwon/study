package com.keunwon.jwt

import com.keunwon.jwt.domain.AuthenticationCode
import com.keunwon.jwt.domain.AuthenticationCodeRepository
import java.util.*

class InmemoryAuthenticationCodeRepository : InmemoryRepository<AuthenticationCode>(), AuthenticationCodeRepository {
    override fun findById(id: Long): Optional<AuthenticationCode> = super.findById(id)

    override fun existsById(id: Long): Boolean = super.existsById(id)

    override fun deleteById(id: Long) = super.deleteById(id)

    override fun findFirstByEmailOrderByCreatedAtDesc(email: String): AuthenticationCode? {
        return findAll().filter { it.email == email }.maxByOrNull { it.createdAt }
    }
}
