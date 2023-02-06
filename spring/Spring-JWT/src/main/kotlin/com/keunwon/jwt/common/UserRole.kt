package com.keunwon.jwt.common

enum class UserRole(val title: String) {
    ADMIN("관리자"),
    USER("사용자"),
    GUEST("게스트");

    companion object {
        val DEFAULT_ROLES = listOf(USER.name)
    }
}
