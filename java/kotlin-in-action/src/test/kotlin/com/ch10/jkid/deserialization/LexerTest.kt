package com.ch10.jkid.deserialization

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import java.io.StringReader

internal class LexerTest : DescribeSpec({

    describe("검증") {
        it("testTrivial") {
            verifyTokens(",", Token.COMMA)
        }

        it("testWhitespace") {
            verifyTokens("   ,  \t", Token.COMMA)
        }

        it("testBoolean") {
            verifyTokens("true", Token.TRUE)
            verifyTokens("false", Token.FALSE)
        }

        it("testNull") {
            verifyTokens("null", Token.NullValue)
        }

        it("testEscapeSequences") {
            verifyTokens(""""\\"""", Token.StringValue("\\"))
            verifyTokens(""""\""""", Token.StringValue("\""))
            verifyTokens(""""\/"""", Token.StringValue("/"))
            verifyTokens(""""\n"""", Token.StringValue("\n"))
            verifyTokens(""""\u0041"""", Token.StringValue("A"))
        }

        it("testNullMalformed") {
            verifyMalformed("nulll")
        }

        it("testString") {
            verifyTokens("\"abc\"", Token.StringValue("abc"))
        }

        it("testInt") {
            verifyTokens("0", Token.LongValue(0))
        }

        it("testNegativeInt") {
            verifyTokens("-1", Token.LongValue(-1))
        }

        it("testDouble") {
            verifyTokens("0.0", Token.DoubleValue(0.0))
        }

        it("testNegativeDouble") {
            verifyTokens("-1.0", Token.DoubleValue(-1.0))
        }
    }
}) {
    companion object {
        private fun verifyTokens(text: String, vararg tokens: Token) {
            val lexer = Lexer(StringReader(text))
            for (expectedToken in tokens) {
                expectedToken shouldBe lexer.nextToken()
            }
            lexer.nextToken().shouldBeNull()
        }

        private fun verifyMalformed(text: String) {
            shouldThrowExactly<MalformedJSONException> {
                Lexer(StringReader(text)).nextToken()
            }
        }
    }
}
