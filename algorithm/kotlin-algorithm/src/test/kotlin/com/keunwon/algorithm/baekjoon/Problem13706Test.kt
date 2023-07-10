package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigInteger

internal class Problem13706Test : StringSpec({
    "case_01" {
        val bi = BigInteger("36")
        val actual = Problem13706().solution(bi)
        actual shouldBe BigInteger("6")
    }

    "case_02" {
        val bi = BigInteger("81")
        val actual = Problem13706().solution(bi)
        actual shouldBe BigInteger("9")
    }

    "case_03" {
        val bi = BigInteger("226576")
        val actual = Problem13706().solution(bi)
        actual shouldBe BigInteger("476")
    }
})
