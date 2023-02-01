package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.TokenProviderFixture
import com.keunwon.jwt.TokenProviderFixture.unExpiredDate
import com.keunwon.jwt.api.UserSignApi
import com.keunwon.jwt.domain.UserRole
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class JwtAuthorizationFilterMvcTest {
    var tokenProvider = TokenProviderFixture.testTokenProvider
    lateinit var mockMvc: MockMvc

    @BeforeAll
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(UserSignApi::class)
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
            jsonPath("$.code").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("Authorization 헤더가 비어있습니다"),
        ).andDo { MockMvcResultHandlers.print() }
    }

    @Test
    fun `토큰 인증 타입(Bearer)을 지정하지 않으면 실패(403) 응답`() {
        // given
        val createJwtDto = CreateTokenRequest("test-id", UserRole.DEFAULT_ROLES)
        val accessToken = tokenProvider.generateToken(createJwtDto, unExpiredDate)

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, accessToken)
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("Authorization 헤더 구성이 올바르지 않습니다"),
        )
    }

    @Test
    fun `토큰의 값이 유효하지 않을 경우 실패(403) 응답`() {
        // given
        val token = "dump"

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("토큰이 유효하지 않습니다."),
        )
    }

    @Test
    fun `만료된 토큰을 함께 보내면 실패(403) 응답`() {
        // given
        val createJwtDto = CreateTokenRequest("test-id", UserRole.DEFAULT_ROLES)
        val expiredToken = tokenProvider.generateToken(createJwtDto, TokenProviderFixture.expiredDate)

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $expiredToken")
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("유효기간이 만료된 토큰입니다."),
        )
    }

    @Test
    fun `임의로 생성한 토큰을 함께 보내면 실패(403) 응답`() {
        // given
        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"

        // when, then
        mockMvc.perform(
            get("/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
        ).andExpectAll(
            status().isForbidden,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value(HttpStatus.FORBIDDEN.value()),
            jsonPath("$.message").value("시그니처 연산에 실패하였습니다.")
        )
    }
}
