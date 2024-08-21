package com.keunwon.algorithm.leetcode

import java.util.*

class `735_Asteroid_Collision` {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val stack = Stack<Int>()
        for (i in asteroids.indices) {
            while (stack.isNotEmpty() && stack.peek() > 0 && asteroids[i] < 0) {
                val diff = stack.peek() + asteroids[i]
                when {
                    diff < 0 -> stack.pop()
                    diff > 0 -> asteroids[i] = 0
                    else -> {
                        stack.pop()
                        asteroids[i] = 0
                    }
                }
            }
            if (asteroids[i] != 0) stack.push(asteroids[i])
        }
        return stack.toIntArray()
    }
}

fun main() {
    `735_Asteroid_Collision`().asteroidCollision(intArrayOf(5, 10, -5))
        .also { println(it.joinToString(", ")) } // [5,10]

    `735_Asteroid_Collision`().asteroidCollision(intArrayOf(8, -8))
        .also { println(it.joinToString(", ")) } // []

    `735_Asteroid_Collision`().asteroidCollision(intArrayOf(10, 2, -5))
        .also { println(it.joinToString(", ")) } // 10
}
