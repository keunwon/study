package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem20057Test : StringSpec({
    "case-1" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 10, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
        )
        val actual = Problem20057().solution(map)
        actual shouldBe 10
    }

    "case-2" {
        val map = arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 100, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
        )
        val actual = Problem20057().solution(map)
        actual shouldBe 85
    }

    "case-3" {
        val map = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(1, 2, 3, 0, 5, 6, 7),
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
        )
        val actual = Problem20057().solution(map)
        actual shouldBe 139
    }

    "case-4" {
        val map = arrayOf(
            intArrayOf(100, 200, 300, 400, 200),
            intArrayOf(300, 243, 432, 334, 555),
            intArrayOf(999, 111, 0, 999, 333),
            intArrayOf(888, 777, 222, 333, 900),
            intArrayOf(100, 200, 300, 400, 500),
        )
        val actual = Problem20057().solution(map)
        actual shouldBe 7501
    }

    "case-5" {
        val map = arrayOf(
            intArrayOf(0, 0, 100, 0, 0),
            intArrayOf(0, 0, 100, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 100, 0, 0),
            intArrayOf(0, 0, 100, 0, 0),
        )
        val actual = Problem20057().solution(map)
        actual shouldBe 283
    }

    "case-6" {
        val map = arrayOf(
            intArrayOf(193, 483, 223, 482, 858, 274, 847, 283, 748),
            intArrayOf(484, 273, 585, 868, 271, 444, 584, 293, 858),
            intArrayOf(828, 384, 382, 818, 347, 858, 293, 999, 727),
            intArrayOf(818, 384, 727, 373, 636, 141, 234, 589, 991),
            intArrayOf(913, 564, 555, 827, 0, 999, 123, 123, 123),
            intArrayOf(321, 321, 321, 983, 982, 981, 983, 980, 990),
            intArrayOf(908, 105, 270, 173, 147, 148, 850, 992, 113),
            intArrayOf(943, 923, 982, 981, 223, 131, 222, 913, 562),
            intArrayOf(752, 572, 719, 590, 551, 179, 141, 137, 731),
        )
        val actual = Problem20057().solution(map)
        actual shouldBe 22961
    }
})
