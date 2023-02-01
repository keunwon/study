package com.keunwon.jwt.domain

import org.springframework.data.repository.CrudRepository

fun UserTokenRepository.getByUserId(userId: Long): UserToken = findByUserId(userId)
    ?: throw IllegalArgumentException("토큰 정보가 존재하지 않습니다.")

interface UserTokenRepository : CrudRepository<UserToken, Long> {
    fun findByUserId(userId: Long): UserToken?
}
