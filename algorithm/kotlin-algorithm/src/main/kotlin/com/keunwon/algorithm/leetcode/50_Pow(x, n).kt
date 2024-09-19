package com.keunwon.algorithm.leetcode

class `50_Pow(x, n)` {
    fun myPow(x: Double, n: Int): Double {
        return dfs(x, n.toLong())
    }

    private fun dfs(x: Double, n: Long): Double {
        if (n == 0L) return 1.0
        if (n < 0) return dfs(x, -n)

        return if (n % 2 == 1L) {
            x * dfs(x * x, (n - 1) / 2)
        } else {
            dfs(x * x, n / 2)
        }
    }
}

fun main() {
    `50_Pow(x, n)`().myPow(2.0, 10).also { println(it) } // 1024.00000
    `50_Pow(x, n)`().myPow(2.1, 3).also { println(it) } // 9.26100
    `50_Pow(x, n)`().myPow(2.0, -2).also { println(it) } // 0.25000
    `50_Pow(x, n)`().myPow(2.00000, -2147483648).also { println(it) } // 0.00
}
