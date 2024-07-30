package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson258712Test : StringSpec({
    "case-1" {
        val friends = arrayOf("muzi", "ryan", "frodo", "neo")
        val gifts = arrayOf(
            "muzi frodo",
            "muzi frodo",
            "ryan muzi",
            "ryan muzi",
            "ryan muzi",
            "frodo muzi",
            "frodo ryan",
            "neo muzi"
        )

        val actual = Lesson258712().solution(friends, gifts)

        actual shouldBe 2
    }

    "case-2" {
        val friends = arrayOf("joy", "brad", "alessandro", "conan", "david")
        val gifts =
            arrayOf("alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david")

        val actual = Lesson258712().solution(friends, gifts)

        actual shouldBe 4
    }

    "case-3" {
        val friends = arrayOf("a", "b", "c")
        val gifts = arrayOf("a b", "b a", "c a", "a c", "a c", "c a")

        val actual = Lesson258712().solution(friends, gifts)

        actual shouldBe 0
    }
})
