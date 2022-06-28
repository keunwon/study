package com.ch07.property

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields

internal class PersonTest : DescribeSpec({

    describe("Person") {
        it("프로퍼티의 값을 변경합니다") {
            val age = 40
            val salary = 4_000_000

            person.apply {
                this.age = age
                this.salary = salary
            }.shouldBeEqualToIgnoringFields(Person("", age, salary), Person::name)
        }
    }
}) {
    companion object {
        val person = Person(name = "홍길동", age = 20, salary = 3_000_000)
    }
}
