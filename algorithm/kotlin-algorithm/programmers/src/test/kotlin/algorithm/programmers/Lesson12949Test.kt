package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12949Test : StringSpec({
    "case-1" {
        val arr1 = arrayOf(
            intArrayOf(1, 4),
            intArrayOf(3, 2),
            intArrayOf(4, 1)
        )
        val arr2 = arrayOf(
            intArrayOf(3, 3),
            intArrayOf(3, 3)
        )

        val actual = Lesson12949().solution(arr1, arr2)
        actual shouldBe arrayOf(
            intArrayOf(15, 15),
            intArrayOf(15, 15),
            intArrayOf(15, 15)
        )
    }

    "case-2" {
        val arr1 = arrayOf(
            intArrayOf(2, 3, 2),
            intArrayOf(4, 2, 4),
            intArrayOf(3, 1, 4)
        )
        val arr2 = arrayOf(
            intArrayOf(5, 4, 3),
            intArrayOf(2, 4, 1),
            intArrayOf(3, 1, 1)
        )

        val actual = Lesson12949().solution(arr1, arr2)

        actual shouldBe arrayOf(intArrayOf(22, 22, 11), intArrayOf(36, 28, 18), intArrayOf(29, 20, 14))
    }
})
