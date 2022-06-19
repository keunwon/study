package com.ch06.`as`

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue

internal class PersonTest : DescribeSpec({

    describe("Person") {
        it("'==' 객체 비교") {
            val p1 = Person("Dmitry", "Jemerov")
            val p2 = Person("Dmitry", "Jemerov")

            (p1 == p2).shouldBeTrue()
        }
    }
})
