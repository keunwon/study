package chapter03.item47

inline class Name(private val value: String) {
    fun greet() {
        println("Hello, I am $value")
    }
}

fun main() {
    val name = Name("Marcin")

}
