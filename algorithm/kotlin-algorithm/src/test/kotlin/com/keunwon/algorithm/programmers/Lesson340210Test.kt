package com.keunwon.algorithm.programmers 
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson340210Test : StringSpec({
    "case-1" {
        val expressions = arrayOf("14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44")
        val actual = Lesson340210().solution(expressions)
        actual shouldBe arrayOf("13 - 6 = 5")
    }

    "case-2" {
        val expressions = arrayOf("1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X")
        val actual = Lesson340210().solution(expressions)
        actual shouldBe arrayOf("1 + 5 = ?", "1 + 2 = 3")
    }

    "case-3" {
        val expressions = arrayOf("10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X")
        val actual = Lesson340210().solution(expressions)
        actual shouldBe arrayOf("10 - 2 = 4", "3 + 3 = 10", "33 + 33 = 110")
    }

    "case-4" {
        val expressions = arrayOf("2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X")
        val actual = Lesson340210().solution(expressions)
        actual shouldBe arrayOf("2 + 2 = 4", "7 + 4 = ?", "5 - 5 = 0")
    }

    "case-5" {
        val expressions = arrayOf("2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X")
        val actual = Lesson340210().solution(expressions)
        actual shouldBe arrayOf("2 + 2 = 4", "7 + 4 = 12", "8 + 4 = 13")
    }
})
