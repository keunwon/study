package com.keunwon.algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class Problem17413Test : StringSpec({
    "case" {
        forAll(
            row("baekjoon online judge", "noojkeab enilno egduj"),
            row("<open>tag<close>", "<open>gat<close>"),
            row("<ab cd>ef gh<ij kl>", "<ab cd>fe hg<ij kl>"),
            row("one1 two2 three3 4fourr 5five 6six", "1eno 2owt 3eerht rruof4 evif5 xis6"),
            row("<int><max>2147483647<long long><max>9223372036854775807",
                "<int><max>7463847412<long long><max>7085774586302733229"),
            row("<problem>17413<is hardest>problem ever<end>", "<problem>31471<is hardest>melborp reve<end>"),
            row("<   space   >space space space<    spa   c e>", "<   space   >ecaps ecaps ecaps<    spa   c e>")
        ) { text, result ->
            val actual = Problem17413().solution(text).trim()
            actual shouldBe result
        }
    }
})
