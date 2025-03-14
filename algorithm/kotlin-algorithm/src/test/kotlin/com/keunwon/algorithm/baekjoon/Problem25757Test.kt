package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem25757Test : StringSpec({
    "case-1" {
        val type = 'Y'
        val name = arrayOf(
            "lms0806",
            "lms0806",
            "exponentiale",
            "lms0806",
            "jthis",
            "lms0806",
            "leo020630",
        )

        val actual = Problem25757().solution(type, name)

        actual shouldBe 4
    }

    "case-2" {
        val type = 'F'
        val names = arrayOf(
            "lms0806",
            "powergee",
            "skeep194",
            "lms0806",
            "tony9402",
            "lms0806",
            "wider93",
            "lms0806",
            "mageek2guanaah",
            "lms0806",
            "jthis",
            "lms0806",
        )

        val actual = Problem25757().solution(type, names)

        actual shouldBe 3
    }

    "case-3" {
        val type = 'O'
        val names = arrayOf(
            "lms0806",
            "mageek2guanaah",
            "jthis",
            "lms0806",
            "exponentiale",
            "lms0806",
            "leo020630",
            "lms0806",
            "powergee",
            "lms0806",
            "skeep194",
            "lms0806",
        )

        val actual = Problem25757().solution(type, names)

        actual shouldBe 2
    }
})
