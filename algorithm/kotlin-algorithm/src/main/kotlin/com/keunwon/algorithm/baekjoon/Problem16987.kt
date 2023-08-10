package com.keunwon.algorithm.baekjoon

/**
 * Title: 계란으로 계란치기
 * Level: 골드-5
 **/
class Problem16987 {
    private lateinit var eggs: List<Egg>
    private var n = 0
    private var answer = 0

    fun solution(n: Int, arr: Array<Pair<Int, Int>>): Int {
        this.n = n
        this.eggs = arr.map { Egg(it.first, it.second) }

        dfs(0, 0)
        return answer
    }

    private fun dfs(index: Int, count: Int) {
        if (index == n) {
            answer = answer.coerceAtLeast(count)
            return
        }

        if (eggs[index].isBroken || count == n - 1) {
            dfs(index + 1, count)
            return
        }

        for (i in 0 until n) {
            if (i == index || eggs[i].isBroken) continue

            var tmpCount = count

            eggs[index].hit(eggs[i])

            if (eggs[index].isBroken) tmpCount++
            if (eggs[i].isBroken) tmpCount++
            dfs(index + 1, tmpCount)

            eggs[index].rollbackHit(eggs[i])
        }
    }

    private class Egg(
        durability: Int,
        val weight: Int,
    ) {
        var durability: Int = durability
            private set

        val isBroken: Boolean
            get() = durability <= 0


        fun hit(other: Egg) {
            durability -= other.weight
            other.durability -= weight
        }

        fun rollbackHit(other: Egg) {
            durability += other.weight
            other.durability += weight
        }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!.split(" ")
            .let { it[0].toInt() to it[1].toInt() }
    }

    Problem16987().solution(n, arr).also(::println)
}
