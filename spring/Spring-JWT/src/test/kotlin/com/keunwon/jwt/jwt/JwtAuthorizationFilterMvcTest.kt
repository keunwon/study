package com.keunwon.jwt.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.TokenProviderFixture
import com.keunwon.jwt.api.UserApi
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class JwtAuthorizationFilterMvcTest {
    var tokenProvider = TokenProviderFixture.testTokenProvider
    lateinit var mockMvc: MockMvc

    @BeforeAll
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(UserApi::class)
            .addFilter<StandaloneMockMvcBuilder>(JwtAuthorizationFilter(tokenProvider, jacksonObjectMapper()))
            .build()
    }

    @Test
    fun `토큰을 보내지 않으면 실패(403) 응답`() {
        // when, then
        mockMvc.perform(
            get("/")
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.status").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("토큰이 비어있거나 존재하지 않습니다."),
        ).andDo { MockMvcResultHandlers.print() }
    }

    @Test
    fun `토큰 인증 타입(Bearer)을 지정하지 않으면 실패(403) 응답`() {
        // given
        val authentication = UsernamePasswordAuthenticationToken("홍길동", "password")
        val token = tokenProvider.generateToken(authentication)

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.status").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("지원하지 않는 토큰 인가 타입입니다."),
        )
    }

    @Test
    fun `토큰의 값이 유효하지 않을 경우 실패(403) 응답`() {
        // given
        val token = "dump string"

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.status").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("토큰이 유효하지 않습니다."),
        )
    }

    @Test
    fun `만료된 토큰을 함께 보내면 실패(403) 응답`() {
        // given
        val authenticationToken = UsernamePasswordAuthenticationToken("홍길동", "password")
        val token = tokenProvider.generateToken(authenticationToken, Date(Date().time - 10000))

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.status").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("유효기간이 만료된 토큰입니다."),
        )
    }

    @Test
    fun `임의로 생성한 토큰을 함께 보내면 실패(403) 응답`() {
        // given
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.status").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("시그니처 연산에 실패하였습니다.")
        )
    }
}
