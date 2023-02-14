package com.github.keunwon.userauth.usertoken

import org.springframework.data.repository.CrudRepository

fun UserTokenRepository.getByUserId(userId: Long): UserToken {
    return findByUserId(userId) ?: throw IllegalArgumentException("토큰 정보가 존재하지 않습니다.")
}

interface UserTokenRepository : CrudRepository<UserToken, Long> {
    fun findByUserId(userId: Long): UserToken?
}
