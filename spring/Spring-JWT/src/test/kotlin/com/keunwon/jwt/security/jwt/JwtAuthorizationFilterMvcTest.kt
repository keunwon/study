package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.TestHomeController
import com.keunwon.jwt.createToken
import com.keunwon.jwt.testObjectMapper
import com.keunwon.jwt.testTokenProvider
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockHttpServletRequestDsl
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class JwtAuthorizationFilterMvcTest {
    lateinit var mockMvc: MockMvc

    @BeforeAll
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(TestHomeController)
            .addFilter<StandaloneMockMvcBuilder>(JwtAuthorizationFilter(testTokenProvider, testObjectMapper))
            .build()
    }

    @Test
    fun `유효한 accessToken 사용하면 정상 응답`() {
        // given
        val accessToken = createToken().value

        // when, then
        mockMvc.get("/") {
            bearer(accessToken)
        }.andExpect {
            status { isOk() }
            content { string("ok") }
        }
    }

    @Test
    fun `토큰을 보내지 않으면 실패(403) 응답`() {
        // when, then
        thenForbiddenResult(null) {
            "Authorization 헤더가 비어있습니다"
        }
    }

    @Test
    fun `토큰 인증 타입(Bearer)을 지정하지 않으면 실패(403) 응답`() {
        // given
        val accessToken = createToken(expired = false).value

        // when, then
        thenForbiddenResult(accessToken) {
            "Authorization 헤더 구성이 올바르지 않습니다"
        }
    }

    @Test
    fun `토큰의 값이 유효하지 않을 경우 실패(403) 응답`() {
        // given
        val token = "dump"

        // when, then
        thenForbiddenResult("Bearer $token") {
            "토큰이 유효하지 않습니다."
        }
    }

    @Test
    fun `만료된 토큰을 함께 보내면 실패(403) 응답`() {
        val expiredToken = createToken(expired = true).value

        thenForbiddenResult("Bearer $expiredToken") {
            "유효기간이 만료된 토큰입니다."
        }
    }

    private fun thenForbiddenResult(bearerToken: String?, message: () -> String) {
        mockMvc.get("/") {
            bearerToken?.let {
                header(HttpHeaders.AUTHORIZATION, bearerToken)
            }
        }.andExpect {
            status { isForbidden() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.code").value(HttpStatus.FORBIDDEN.value())
            jsonPath("$.message").value(message())
        }
    }
}

fun MockHttpServletRequestDsl.bearer(token: String) {
    header(HttpHeaders.AUTHORIZATION, "Bearer $token")
}
