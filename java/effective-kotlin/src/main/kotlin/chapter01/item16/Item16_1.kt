package chapter01.item16

private var name: String? = null
    get() = field?.uppercase()
    set(value) {
        if (!value.isNullOrBlank()) {
            field = value
        }
    }

private val text = "apple"

private val fullName: String
    get() = text

fun main() {
    println(fullName)
}
