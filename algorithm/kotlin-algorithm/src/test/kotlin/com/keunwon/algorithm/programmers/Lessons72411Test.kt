package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class Lessons72411Test : StringSpec({
    "case_01" {
        val orders = arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH")
        val course = intArrayOf(2, 3, 4)

        val actual = Lessons72411().solution(orders, course)

        actual shouldBe arrayOf("AC", "ACDE", "BCFG", "CDE")
    }

    "case_02" {
        val orders = arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD")
        val course = intArrayOf(2, 3, 5)

        val actual = Lessons72411().solution(orders, course)

        actual shouldBe arrayOf("ACD", "AD", "ADE", "CD", "XYZ")
    }

    "case_03" {
        val orders = arrayOf("XYZ", "XWY", "WXA")
        val course = intArrayOf(2, 3, 4)

        val actual = Lessons72411().solution(orders, course)

        actual shouldBe arrayOf("WX", "XY")
    }
})
