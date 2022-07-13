package com.ch11

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class HTMLTagsKtTest : DescribeSpec({

    describe("createTable") {
        it("html 내용을 문자열로 반환합니다") {
            val htmlToString = createTable().toString()

            htmlToString shouldBe """
                <table><tr><td></td><td></td></tr><tr><td></td><td></td><td></td></tr></table>
            """.trimIndent()
        }
    }

    describe("createAnotherTable") {
        it("html 내용을 문자열로 반환합니다") {
            val htmlToString = createAnotherTable().toString()

            htmlToString shouldBe """
                <table><tr><td></td></tr><tr><td></td></tr></table>
            """.trimIndent()
        }
    }

    describe("createSimpleTable") {
        it("html 내용을 문자열로 반환합니다") {
            val htmlToString = createAnotherTable2()

            htmlToString shouldBe """
                <table>
                  <tr>
                    <td>1</td>
                    <td>one</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>two</td>
                  </tr>
                </table>
                
            """.trimIndent()
        }
    }

    describe("createSimpleTable()") {
        it("html 내용을 문자열로 반환합니다") {
            val htmlToString = createSimpleTable()

            htmlToString shouldBe """
                <table>
                  <tr>
                    <td>cell</td>
                  </tr>
                </table>
                
            """.trimIndent()
        }
    }

    describe("buildDropdown") {
        it("html 내용을 문자열로 반환합니다") {
            val htmlToString = buildDropdown()

            htmlToString shouldBe """
                <div class="dropdown"><button class="btn dropdown-toggle">Dropdown<span class="caret"></span></button>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="dropdown-header">Header</li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </div>
                
            """.trimIndent()
        }
    }
})
