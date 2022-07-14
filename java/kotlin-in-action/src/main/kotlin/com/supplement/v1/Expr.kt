package com.supplement

sealed class Expr

data class Num(val value: Int) : Expr()
data class Sum(val left: Expr, val right: Expr): Expr()

fun eval(e: Expr): Int = when (e) {
    is Num -> e.value
    is Sum -> eval(e.left) + eval(e.right)
}

fun main() {
    val v = Sum(Num(1), Sum(Num(10), Num(20)))
    println(v)
    println(eval(v))
}
