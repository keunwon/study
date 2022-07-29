package chapter01.item02

private val numbers = listOf(1, 2, 3)

fun scope1() {
    var num: Int
    for (i in numbers.indices) {
        num = numbers[i]
        println("Num at $i is $num")
    }
}

fun scope2() {
    for (i in numbers.indices) {
        val num = numbers[i]
        println("Num at $i is $num")
    }
}

fun scope3() {
    for ((index, num) in numbers.withIndex()) {
        println("Num at $index is $num")
    }
}

fun main() {
    scope1()
    scope2()
    scope3()
}
