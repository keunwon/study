package chapter02.item27

data class Id(private val id: Int)

private var nextId = 0
fun getNextId(): Id = Id(nextId++)

fun main() {
    println(getNextId())
    println(getNextId())
    println(getNextId())
}
