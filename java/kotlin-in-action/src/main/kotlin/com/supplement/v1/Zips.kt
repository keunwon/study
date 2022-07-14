package com.supplement

val nums = listOf(1, 2, 3)
val names = listOf("One", "Two", "Three")

fun printNumsWithNames(nums: List<Int>, names: List<String>) =
    (nums zip names).forEach { (num, name) -> println("$num = $name") }

