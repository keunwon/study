package chapter02.item24

private class Cup2<in T>
private open class Dog2
private class Puppy2 : Dog2()

fun main() {
    //val b: Cup2<Dog2> = Cup2<Puppy2>() error
    val a : Cup2<Puppy2> = Cup2<Dog2>()

    //val anys: Cup2<Any> = Cup2<Int>() error
    val nothins: Cup2<Nothing> = Cup2<Int>()
}
