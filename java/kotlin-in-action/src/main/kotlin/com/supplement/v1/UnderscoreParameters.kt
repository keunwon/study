package com.supplement.v1

data class YMD(val year: Int, val month: Int, val day: Int)

typealias YMDFUN = (YMD) -> Unit

fun applyYMD(v: YMD, f: YMDFUN) = f(v)

val now = YMD(2017, 10, 9)
val ymd = YMD(1919, 3, 1)

fun main() {
    val (year, _, _) = ymd
    println(year)

    applyYMD(now) { (year, month, _) -> println("year = $year, month = $month") }
}
