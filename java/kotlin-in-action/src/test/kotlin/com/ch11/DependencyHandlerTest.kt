package com.ch11

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class DependencyHandlerTest : DescribeSpec({

    beforeEach {
        outputStream.reset()
        System.setOut(PrintStream(outputStream))
    }

    afterEach {
        System.setOut(output)
    }

    describe("DependencyHandler") {
        it("compile dsl") {
            dependencies {
                compile(coordinate1)
                compile(coordinate2)
            }

            outputStream.toString() shouldBe """
                Added dependency on $coordinate1
                Added dependency on $coordinate2
                
            """.trimIndent()
        }
    }
}) {
    companion object {
        val outputStream = ByteArrayOutputStream()
        val output = System.out!!

        const val coordinate1 = "org.jetbrains.kotlin:kotlin-reflect:1.0.0"
        const val coordinate2 = "org.jetbrains.kotlin:kotlin-stdlib:1.0.0"

        val dependencies = DependencyHandler()
    }
}
