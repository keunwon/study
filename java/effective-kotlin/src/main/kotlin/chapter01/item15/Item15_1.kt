package chapter01.item15

open class Person()

class User : Person() {
    private var beersDrunk: Int = 0

    fun drinkBeers(num: Int) {
        this.beersDrunk += num
    }
}

fun <T : Comparable<T>> List<T>.quickSort(): List<T> {
    if (size < 2) return this
    val pivot = this.first()
    val (smaller, bigger) = this.drop(1)
        .partition { it < pivot }
    return smaller.quickSort() + pivot + bigger.quickSort()
}

fun main() {
    println(listOf(3, 2, 5, 1, 6).quickSort())
    println(listOf("C", "D", "A", "B").quickSort())
}
