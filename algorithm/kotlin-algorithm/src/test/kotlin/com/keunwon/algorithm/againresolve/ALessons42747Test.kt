package com.keunwon.algorithm.againresolve

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ALessons42747Test : StringSpec({
    "기본 예제" {
        val citations = intArrayOf(3, 0, 6, 1, 5)

        val actual = ALessons42747().solution(citations)

        actual shouldBe 3
    }
})
