package com.keunwon.algorithm.programmers

import kotlin.math.min

// todo
class Lesson87391 {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var (left, right, top, bottom) = intArrayOf(y, y, x, x)

        for ((dir, num) in queries.reversed()) {
            when (dir) {
                0 -> {
                    // ->
                    if (left != 0) left += num
                    right = min(right + num, m - 1)
                    if (left > m - 1) return 0
                }

                1 -> {

                }

                2 -> {

                }

                3 -> {

                }
            }
        }
        return (bottom.toLong() - top + 1) * (right.toLong() - left + 1)
    }
    //열 번호가 감소하는 방향으로 dx칸 이동하는 쿼리 (query(0, dx))
    //열 번호가 증가하는 방향으로 dx칸 이동하는 쿼리 (query(1, dx))
    //행 번호가 감소하는 방향으로 dx칸 이동하는 쿼리 (query(2, dx))
    //행 번호가 증가하는 방향으로 dx칸 이동하는 쿼리 (query(3, dx))
}
