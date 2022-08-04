package chapter02.item27

const val MIN_PASSWORD_LENGTH = 7

fun isPasswordValid(text: String): Boolean {
    if (text.length < MIN_PASSWORD_LENGTH) return false
    //...
    return true
}
