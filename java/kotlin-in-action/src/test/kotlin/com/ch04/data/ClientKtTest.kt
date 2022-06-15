package com.ch04.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeUnique
import io.kotest.matchers.should
import io.kotest.matchers.string.shouldStartWith

internal class ClientKtTest : DescribeSpec({

    describe("data class Client") {

        context("값이 동일한 상태에서 '==' 비교시 true 반환합니다") {
            val client1 = Client(name = "홍길동", postalCode = 100)
            val client2 = Client(name = "홍길동", postalCode = 100)

            (client1 == client2).shouldBeTrue()
        }

        context("toString") {
            val client = Client(name = "홍길동", postalCode = 100)

            client.toString() shouldStartWith "Client(name="
        }

        context("hashCode") {
            val clients = hashSetOf(
                Client(name = "홍길동", postalCode = 100),
                Client(name = "세종대왕", postalCode = 100)
            )

            clients.contains(Client(name = "홍길동", postalCode = 100)).shouldBeTrue()
        }

        context("copy() 메서드") {
            val client1 = Client(name = "홍길동", postalCode = 100)

            it("'==' 비교시 true 반환") {
                val copyClient = client1.copy()

                (client1 == copyClient).shouldBeTrue()
            }

            it("hashCode") {
                val clients = hashSetOf(
                    client1,
                    client1.copy(name = "세종대왕", postalCode = 100),
                    client1.copy(name = "세종대왕", postalCode = 200)
                )
                val searchTarget = Client(name = "세종대왕", postalCode = 100)
                clients.shouldBeUnique()
                    .contains(searchTarget).shouldBeTrue()
            }
        }
    }
})