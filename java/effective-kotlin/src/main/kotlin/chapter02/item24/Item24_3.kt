package chapter02.item24

private open class Dog3
private class Puppy3 : Dog3()
private class Hound : Dog3()

class Box<out T> {
    private var value: T? = null

    private fun set(value: T) {
        this.value = value
    }

    fun get(): T = value ?: error("value not set")
}

fun main() {
    val takeDog: (dog: Dog3) -> Unit = {}

    takeDog(Dog3())
    takeDog(Puppy3())
    takeDog(Hound())
}
