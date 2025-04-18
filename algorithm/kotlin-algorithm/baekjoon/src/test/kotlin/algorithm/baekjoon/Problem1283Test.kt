package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1283Test : StringSpec({
    "case-1" {
        val words = arrayOf(
            "New",
            "Open",
            "Save",
            "Save As",
            "Save All",
        )
        val actual = Problem1283().solution(words)
        actual shouldBe arrayOf(
            "[N]ew",
            "[O]pen",
            "[S]ave",
            "Save [A]s",
            "Sa[v]e All",
        )
    }

    "case-2" {
        val words = arrayOf(
            "New window",
            "New file",
            "Copy",
            "Undo",
            "Format",
            "Font",
            "Cut",
            "Paste",
        )
        val actual = Problem1283().solution(words)
        actual shouldBe arrayOf(
            "[N]ew window",
            "New [f]ile",
            "[C]opy",
            "[U]ndo",
            "F[o]rmat",
            "Fon[t]",
            "Cut",
            "[P]aste",
        )
    }
})
