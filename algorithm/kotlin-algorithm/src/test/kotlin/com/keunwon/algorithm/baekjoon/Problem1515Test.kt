package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1515Test : StringSpec({
    "case-1" {
        val str = "1234"
        val actual = Problem1515().solution(str)
        actual shouldBe 4
    }

    "case-2" {
        val str = "234092"
        val actual = Problem1515().solution(str)
        actual shouldBe 20
    }

    "case-3" {
        val str = "999909"
        val actual = Problem1515().solution(str)
        actual shouldBe 49
    }

    "case-4" {
        val str = "82340329923"
        val actual = Problem1515().solution(str)
        actual shouldBe 43
    }

    "case-5" {
        val str = "32098221"
        val actual = Problem1515().solution(str)
        actual shouldBe 61
    }

    "case-6" {
        val str = "1111111"
        val actual = Problem1515().solution(str)
        actual shouldBe 14
    }

    "case-7" {
        val str = "00000000000000000000000000000000000000000000000000000000000000000000000"
        val actual = Problem1515().solution(str)
        actual shouldBe 400
    }

    "case-8" {
        val str = "345029834023049820394802334909240982039842039483294792934790209"
        val actual = Problem1515().solution(str)
        actual shouldBe 279
    }
})
