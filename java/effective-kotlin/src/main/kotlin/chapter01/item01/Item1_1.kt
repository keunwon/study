package chapter01.item01

fun calculate(): Int {
    println("Calculating")
    return 42
}

val fizz = calculate()
val buzz
    get() = fizz

fun main() {
    println("main")
    println(fizz)
    println(fizz)

    println(buzz)
    println(buzz)
}
