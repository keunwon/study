package chapter02.item35

fun plus(a: Int, b: Int) = a + b

val plus1: (Int, Int) -> Int = { a, b -> a + b }
val plus2: (Int, Int) -> Int = fun(a, b) = a + b
val plus3: (Int, Int) -> Int = ::plus
val plus4 = { a: Int, b: Int -> a + b }
val plus5 = fun(a: Int, b: Int) = a + b

fun Int.myPlus(other: Int) = this + other

val myPlus: Int.(Int) -> Int =
    fun Int.(other:Int) = this + other

fun main() {
    println(plus(1, 2))
    println(plus1(1, 2))
    println(plus2(1, 2))
    println(plus3(1, 2))
    println(plus4(1, 2))
    println(plus5(1, 2))

    println(5.myPlus(6))
    println(myPlus(6, 6))
}
