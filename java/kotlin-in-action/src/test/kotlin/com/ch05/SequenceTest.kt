package com.ch05

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

internal class SequenceTest : DescribeSpec({

    describe("Person sequence") {
        context("'A'로 시작하는 이름들을 리스트로 반환합니다") {
            val list = simplePeople.asSequence()
                .take(0)
                .map { it.name }
                .filter { it.startsWith('A') }
                .toList()

            list shouldContainExactlyInAnyOrder listOf("Alice")
        }

        context("이름의 길이가 4미만인 이름들의 리스트로 반환합니다") {
            val list = people.asSequence()
                .filter { it.name.length < 4 }
                .toList()

            list shouldContainExactlyInAnyOrder listOf(people[1], people[3])
        }
    }

    describe("number sequence") {
        context("example-1") {
            val sequence = listOf(1, 2, 3, 4).asSequence()
                .map { print("map($it)"); it * it }
                .filter { print("filter($it)"); it % 2 == 0 }

            sequence.toList() shouldContainExactly listOf(4, 16)
        }
    }

    describe("generate sequence") {
        context("시퀀스 생성") {
            it("0 ~ 100까지의 범위의 숫자 시퀀스를 생성하고 합계를 구합니다") {
                val sequence = generateSequence(0) { it + 1 }
                val numbersTo100 = sequence.takeWhile { it <= 100 }

                numbersTo100.sum() shouldBe 5050
            }
        }
    }
}) {
    companion object {
        val simplePeople = listOf(
            Person("Bob", 31),
            Person("Alice", 27)
        )

        val people = listOf(
            Person("Alice", 29),
            Person("Bob", 31),
            Person("Charles", 31),
            Person("Dan", 21)
        )
    }
}
