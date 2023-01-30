package com.keunwon.jwt.domain

import org.springframework.data.repository.CrudRepository

interface UserTokenRepository : CrudRepository<UserToken, Long> {

    fun findByUserId(userId: Long): UserToken?
}
