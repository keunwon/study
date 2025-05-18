package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson340213Test : StringSpec({
    "case-1" {
        val actual = Lesson340213().solution(
            video_len = "34:33",
            pos = "13:00",
            op_start = "00:55",
            op_end = "02:55",
            commands = arrayOf("next", "prev"),
        )
        actual shouldBe "13:00"
    }

    "case-2" {
        val actual = Lesson340213().solution(
            video_len = "10:55",
            pos = "00:05",
            op_start = "00:15",
            op_end = "06:55",
            commands = arrayOf("prev", "next", "next")
        )
        actual shouldBe "06:55"
    }

    "case-3" {
        val actual = Lesson340213().solution(
            video_len = "07:22",
            pos = "04:05",
            op_start = "00:15",
            op_end = "04:07",
            commands = arrayOf("next")
        )
        actual shouldBe "04:17"
    }
})
