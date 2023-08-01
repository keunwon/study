package com.keunwon.algorithm.programmers

/**
 * Title: 기둥과 보 설치
 * Level: 3
 **/
class Lessons60061 {
    private lateinit var pillars: Array<BooleanArray>
    private lateinit var bos: Array<BooleanArray>

    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        this.pillars = Array(n + 1) { BooleanArray(n + 1) }
        this.bos = Array(n + 1) { BooleanArray(n + 1) }

        for ((x, y, type, command) in build_frame) {
            when (command) {
                CREATE -> {
                    if (type == PILLAR && checkPillar(x, y)) pillars[y][x] = true
                    else if (type == BO && checkBo(x, y)) bos[y][x] = true
                }
                DELETE -> {
                    val tmpArr = when (type) {
                        PILLAR -> pillars
                        BO -> bos
                        else -> error("")
                    }
                    tmpArr[y][x] = false
                    if (!isDel(n)) tmpArr[y][x] = true
                }
            }
        }

        val answer = mutableListOf<IntArray>()
        for (y in 0..n) {
            for (x in 0..n) {
                if (pillars[y][x]) answer.add(intArrayOf(x, y, 0))
                if (bos[y][x]) answer.add(intArrayOf(x, y, 1))
            }
        }
        return answer.sortedWith(compareBy({ it[0] }, { it[1] })).toTypedArray()
    }

    private fun isDel(n: Int): Boolean {
        for (y in 0..n) {
            for (x in 0..n) {
                if (pillars[y][x] && !checkPillar(x, y)) return false
                if (bos[y][x] && !checkBo(x, y)) return false
            }
        }
        return true
    }

    private fun checkBo(x: Int, y: Int): Boolean = when {
        x > 0 && bos[y][x - 1] && bos[y][x + 1] -> true
        y > 0 && pillars[y - 1][x] || pillars[y - 1][x + 1] -> true
        else -> false
    }

    private fun checkPillar(x: Int, y: Int): Boolean = when {
        y == 0 -> true
        x > 0 && bos[y][x - 1] || bos[y][x] -> true
        y > 0 && pillars[y - 1][x] -> true
        else -> false
    }

    companion object {
        private const val CREATE = 1
        private const val DELETE = 0
        private const val PILLAR = 0
        private const val BO = 1
    }
}
