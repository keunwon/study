package com.github.keunwon.test

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class InmemoryJpaRepositoryTest : BehaviorSpec({
    val testUserRepository = InmemoryTestEntityRepository()

    Given("저장된 데이터가 없는 경우") {
        val testUser = TestEntity("홍길동", "닉네임")

        When("id 값이 '0L' 데이터를 저장하면") {
            testUserRepository.save(testUser)

            Then("ID 값은 1이다") {
                testUser.id shouldBe 1L
                testUser.name shouldBe "홍길동"
                testUser.nickname shouldBe "닉네임"
            }
        }
    }

    Given("id 가 long 타입인 객체를 여러개 저장하는 경우") {
        val testEntities = mutableListOf(
            TestEntity("홍길동1", "닉네임1"),
            TestEntity("홍길동2", "닉네임2"),
            TestEntity("홍길동3", "닉네임3"),
            TestEntity("홍길동4", "닉네임4"),
        )
        testUserRepository.saveAll(testEntities)

        Then("중복이 존재하지 않고 순차적으로 증가하는 ID 값들을 생성합니다.") {
            testEntities.map { it.id } shouldContainExactly listOf(1L, 2L, 3L, 4L)
        }

        When("저장된 id 값을 조회하면") {
            val id = 1L
            val user = testUserRepository.findByIdOrNull(id)

            Then("id 값이 동일한 객체를 반환한다") {
                user.shouldNotBeNull()
                user.id shouldBe 1L
                user.name shouldBe "홍길동1"
                user.nickname shouldBe "닉네임1"
            }
        }

        When("저장된 여러 id 값들을 조회한면") {
            val ids = mutableListOf(1L, 2L, 3L)
            val findEntity = testUserRepository.findAllById(ids)

            Then("id 값이 동일한 객체들을 반환한다") {
                findEntity.shouldNotBeEmpty()

                repeat(ids.size) {
                    testEntities[it].id shouldBe findEntity[it].id
                    testEntities[it] shouldBe findEntity[it]
                }
            }
        }

        Then("저장된 개수를 반환한다") {
            testUserRepository.count() shouldBe testEntities.size
        }

        When("특정 엔티티 기준으로 삭제하면") {
            val removeId = testEntities[0].id
            val removeEntity = testEntities[0]
            testUserRepository.delete(removeEntity)

            Then("삭제 entity id 기준으로 조회 시 null 반환한다") {
                testUserRepository.findByIdOrNull(removeId).shouldBeNull()
            }

            Then("삭제 entity id 기준으로 존재 여부 확인 시 false 반환한다") {
                testUserRepository.existsById(removeId).shouldBeFalse()
            }
        }

        When("특정 id 값을 기준으로 삭제하면") {
            val id = 1L
            testUserRepository.deleteById(id)

            Then("삭제한 id 조회 결과는 null 이다") {
                testUserRepository.findByIdOrNull(id).shouldBeNull()
            }
        }

        When("여러 id 값을 기준으로 삭제하면") {
            val ids = mutableListOf(1L, 2L, 3L)
            testUserRepository.deleteAllById(ids)

            Then("삭제한 id 데이터 조회 시 결과는 null 이다") {
                testUserRepository.findAllById(ids).shouldBeEmpty()
                testUserRepository.findAll() shouldHaveSize 1
            }
        }

        Then("전체 데이터를 삭제하면, 전체 조회 결과는 empty 이다") {
            testUserRepository.deleteAll()
            testUserRepository.findAll().shouldBeEmpty()
        }
    }
})

@Entity
class TestEntity(
    @Column
    val name: String,

    @Column
    var nickname: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
)

interface TestUserRepository : JpaRepository<TestEntity, Long>

class InmemoryTestEntityRepository : InmemoryJpaRepository<TestEntity, Long>(LongPrimaryKeyGenerator()),
    TestUserRepository
