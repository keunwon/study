package chapter01.item01

class UserRepository {
    private val storedUsers = mutableMapOf<Int, String>()

    fun loadAll() = storedUsers
}

class UserHolder {
    private val user = MutableUser(name = "name")

    fun get() = user.copy()
}

data class MutableUser(val name: String)

fun main() {
    val userRepository = UserRepository()
    val storedUsers = userRepository.loadAll()
    storedUsers[4] = "Kirill"

    println(userRepository.loadAll())
}
