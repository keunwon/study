package com.ch04

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

class IntSum(val left: Int, val right: Int) : Expr() {}

fun eval(e: Expr): Int = when (e) {
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.left) + eval(e.right)
    is IntSum -> eval(Expr.Num(e.left)) + eval(Expr.Num(e.right))
}