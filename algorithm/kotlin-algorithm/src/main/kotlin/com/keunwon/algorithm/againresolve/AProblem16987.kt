package com.keunwon.algorithm.againresolve

/**
 * Title: 계란으로 계란치기
 * Level: 골드-5
 **/
class AProblem16987 {
    private lateinit var eggs: List<Egg>
    private var answer = 0

    fun solution(arr: Array<Pair<Int, Int>>): Int {
        this.eggs = arr.map { Egg(it.first, it.second) }
        dfs(0, 0)
        return answer
    }

    private fun dfs(index: Int, count: Int) {
        if (index == eggs.size) {
            answer = answer.coerceAtLeast(count)
            return
        }

        if (eggs[index].isBroken || count == eggs.lastIndex) {
            dfs(index + 1, count)
            return
        }

        for (i in eggs.indices) {
            if (index == i || eggs[i].isBroken) continue

            eggs[index].hit(eggs[i])

            val add = booleanArrayOf(
                eggs[index].isBroken,
                eggs[i].isBroken,
            ).count { it }
            dfs(index + 1, count + add)

            eggs[index].reverseHit(eggs[i])
        }
    }

    private class Egg(
        durability: Int,
        val weight: Int,
    ) {
        var durability = durability
            private set

        val isBroken: Boolean
            get() = durability <= 0

        fun hit(other: Egg) {
            durability -= other.weight
            other.durability -= weight
        }

        fun reverseHit(other: Egg) {
            durability += other.weight
            other.durability += weight
        }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!.split(" ")
            .let { (a, b) -> a.toInt() to b.toInt() }
    }

    AProblem16987().solution(arr).also(::println)
}
