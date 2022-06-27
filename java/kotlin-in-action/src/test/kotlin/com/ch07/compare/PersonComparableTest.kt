package com.ch07.compare

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe

internal class PersonComparableTest : DescribeSpec({

    describe("compareTo") {
        it("성, 이름 순으로 정렬을 합니다") {
            person1.compareTo(person2) shouldBeLessThan 0
            (person1 < person2).shouldBeTrue()
        }

        it("성, 이름이 동일한 경우 0으로 반환합니다") {
            val copyPerson = Person(person1.firstName, person1.lastName)

            copyPerson.compareTo(person1) shouldBe 0
        }
    }
}) {
    companion object {
        val person1 = Person("김", "길동")
        val person2 = Person("나", "길동")
    }
}
