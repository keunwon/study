package chapter01.item05

fun factorial(n: Int): Long {
    require(n >= 0) { "Cannot calculate factorial of $n because it is smaller than 0" }
    return if (n <= 1) 1 else factorial(n - 1) * n
}

fun findClusters(points: List<String>) {
    require(points.isNotEmpty())
    // ...
}

data class User(val email: String?)

fun sendEmail(user: User, message: String) {
    requireNotNull(user.email)
    // ...
}

fun main() {
    println(factorial(5))
    factorial(-1)
}
