package com.ch04.companion

fun getFacebookName(accountId: Int) = "fb:$accountId"

class User private constructor(val nickname: String) {

    companion object {
        fun newSubscribingUser(email: String) =
            User(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) =
            User(getFacebookName(accountId))
    }
}