package chapter02.item35

class User {
    var name: String = ""
    var surname: String = ""
}

val user = User().apply {
    name = "Marcin"
    surname = "Moskala"
}

fun main() {
    println(user)
}
