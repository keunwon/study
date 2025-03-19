package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem22233Test : StringSpec({
    "case-1" {
        val keywords = arrayOf("map", "set", "dijkstra", "floyd", "os")
        val posts = arrayOf("map,dijkstra", "map,floyd")

        val actual = Problem22233().solution(keywords, posts)

        actual shouldBe intArrayOf(3, 2)
    }

    "case-2" {
        val keywords = arrayOf("gt26cw", "1211train")
        val posts = arrayOf("kiwoom,lottegiant", "kbo")

        val actual = Problem22233().solution(keywords, posts)

        actual shouldBe intArrayOf(2, 2)
    }
})
