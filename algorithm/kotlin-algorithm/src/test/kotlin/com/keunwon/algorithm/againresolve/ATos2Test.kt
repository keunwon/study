package com.keunwon.algorithm.againresolve

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ATos2Test : StringSpec({
    "case_01" {
        val arr = arrayOf(
            "20-DE0815",
            "20-CO1299",
            "20-MO0901",
            "20-KE0511",
            "20-SP1102",
            "21-DE0401",
            "21-CO0404",
            "21-MO0794",
            "21-KE0704",
            "21-SP0404",
            "19-DE0401",
            "19-CO0404",
            "19-MO0794",
            "19-KE1204",
            "19-SP0404"
        )

        val actual = ATos2().solution(arr)

        actual shouldBe arrayOf(
            "19-SP0404",
            "19-KE1204",
            "19-MO0794",
            "19-CO0404",
            "19-DE0401",
            "20-SP1102",
            "20-KE0511",
            "20-MO0901",
            "20-CO1299",
            "20-DE0815",
            "21-SP0404",
            "21-KE0704",
            "21-MO0794",
            "21-CO0404",
            "21-DE0401",
        )
    }

    "case_02" {
        val arr = arrayOf(
            "13-DE0401",
            "13-DE0401",
            "22-MO0815",
            "19-MO1299",
            "17-CO0901",
            "14-DE0511",
            "19-KE1102",
            "20-SP0404",
            "20-CO0794",
        )

        val actual = ATos2().solution(arr)

        actual shouldBe arrayOf(
            "13-DE0401",
            "14-DE0511",
            "17-CO0901",
            "19-KE1102",
            "19-MO1299",
            "20-SP0404",
            "20-CO0794",
            "22-MO0815",
        )
    }
})
