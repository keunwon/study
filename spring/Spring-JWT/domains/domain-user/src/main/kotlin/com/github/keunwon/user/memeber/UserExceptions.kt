package com.github.keunwon.user.memeber

class UnIdentifiedUserException(message: String?) : RuntimeException(message)

fun identify(value: Boolean, lazyMessage: () -> Any) {
    if (!value) {
        val message = lazyMessage()
        throw UnIdentifiedUserException(message.toString())
    }
}
