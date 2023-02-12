package com.github.keunwon.user

import com.github.keunwon.repository.InmemoryCrudRepository
import com.github.keunwon.repository.LongPrimaryKeyGenerator
import com.github.keunwon.user.authenticationcode.AuthenticationCode
import com.github.keunwon.user.authenticationcode.AuthenticationCodeRepository
import com.github.keunwon.user.memeber.User
import com.github.keunwon.user.memeber.UserRepository
import com.github.keunwon.user.passwordhistory.UserPasswordHistory
import com.github.keunwon.user.passwordhistory.UserPasswordHistoryRepository

class InmemoryUserRepository
    : InmemoryCrudRepository<User, Long>(LongPrimaryKeyGenerator()), UserRepository {
    override fun findByProfileEmail(email: String): User? {
        return findAll().find { it.profile.email == email }
    }

    override fun existsByProfileEmail(email: String): Boolean {
        return findAll().any { it.profile.email == email }
    }
}

class InmemoryUserPasswordHistoryRepository
    : InmemoryCrudRepository<UserPasswordHistory, Long>(LongPrimaryKeyGenerator()), UserPasswordHistoryRepository {
    override fun getFirstByUserIdOrderByCreatedDateTimeDesc(userId: Long): UserPasswordHistory? {
        return findAll().maxByOrNull { it.userId == userId }
    }

    override fun getAllByUserId(userId: Long): List<UserPasswordHistory> {
        return findAll().filter { it.userId == userId }
    }
}

class InmemoryAuthenticationCodeRepository
    : InmemoryCrudRepository<AuthenticationCode, Long>(LongPrimaryKeyGenerator()), AuthenticationCodeRepository {
    override fun findFirstByEmailOrderByCodeDesc(email: String): AuthenticationCode? {
        //return findAll().maxByOrNull { it.email == email }
        return findAll().filter { it.email == email }.maxByOrNull { it.createLocalDate }
    }
}
