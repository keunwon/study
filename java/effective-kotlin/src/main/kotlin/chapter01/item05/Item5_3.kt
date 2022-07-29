package chapter01.item05

import java.util.*

fun assertTest(num: Int = 1): List<Int> {
    val stack = Stack<Int>()
        .apply { push(10); push(20); push(30) }

    assert(stack.size == num) { "stack 크기가 올바르지 않습니다. size: ${stack.size}, 기대한 크기: $num" }

    return stack.toList()
}

fun main() {
    println(assertTest(3))
    println(assertTest())
}
