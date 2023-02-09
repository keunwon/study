package com.keunwon.jwt.common

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class UserRole(val title: String) {
    ADMIN("관리자"),
    USER("사용자"),
    GUEST("게스트");

    companion object {
        val DEFAULT_ROLES = listOf(USER.name)
    }
}

fun mapGrantedAuthority(vararg userRole: UserRole): List<GrantedAuthority> {
    return userRole.map { SimpleGrantedAuthority(it.name) }
}
