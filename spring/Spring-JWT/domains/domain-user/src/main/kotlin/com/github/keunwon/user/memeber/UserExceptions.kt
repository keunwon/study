package com.github.keunwon.user.memeber

class UnIdentifiedUserException(message: String?) : RuntimeException(message)

class NotMatchUserPasswordException(email: String, failedCount: Int) :
    RuntimeException("사용자 비밀번호가 일치하지 않습니다, email: $email, 실패 횟수: $failedCount")

fun identify(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        val message = lazyMessage()
        throw UnIdentifiedUserException(message.toString())
    }
}
