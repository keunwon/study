package chapter02.item24

private class Cup<out T>
private open class Dog
private class Puppy : Dog()

fun main() {
    val b: Cup<Dog> = Cup<Puppy>()
    //val a: Cup<Puppy> = Cup<Dog> error
    val anys: Cup<Any> = Cup<Int>()
    //val nothings: Cup<Nothing> = Cup<Int> error
}
