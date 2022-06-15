package com.ch04.`interface`

interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscrbingUser(val email: String) : User {
    override val nickname: String
        get() {
            println("method get()")
            return email.substringBefore('@')
        }
}

class FacebookUser(val accountId: Int) : User {
    override val nickname = getFacebookName(accountId)

}

fun getFacebookName(accountId: Int): String {
    println("getFacebookName")
    return "$accountId"
}

interface CustomUser {
    val email: String

    val nickname: String
        get() {
            println("get nickname")
            return email.substringBefore('@')
        }
}

class CustomSubscrbingUser(override val email: String) : CustomUser {}

fun main() {
    val subscrbingUser = SubscrbingUser("test@google.com")
    println(subscrbingUser.nickname)
    println(subscrbingUser.nickname)

    val facebookUser = FacebookUser(4)
    println(facebookUser.nickname)
    println(facebookUser.nickname)

    val customSubscrbingUser = CustomSubscrbingUser("test@google.com")
    println(customSubscrbingUser.email)
    println(customSubscrbingUser.nickname)
    println(customSubscrbingUser.nickname)
}