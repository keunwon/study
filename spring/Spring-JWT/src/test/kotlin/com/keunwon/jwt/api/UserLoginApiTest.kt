package com.keunwon.jwt.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.JwtAuthenticationManagerStub
import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.STRING
import com.keunwon.jwt.TokenProviderFixture.testTokenProvider
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.security.jwt.CreateTokenRequest
import com.keunwon.jwt.security.jwt.JwtLoginAuthenticationFilter
import com.keunwon.jwt.security.jwt.LoginTokenResponse
import com.keunwon.jwt.testObjectMapper
import com.keunwon.jwt.type
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ExtendWith(RestDocumentationExtension::class)
class UserLoginApiTest : RestDocsSupport() {
    private val authenticationManager = JwtAuthenticationManagerStub
    private val customAuthenticationFilter =
        JwtLoginAuthenticationFilter(authenticationManager, testObjectMapper).apply {
            setAuthenticationSuccessHandler(LoginSuccessHandlerStub())
        }

    lateinit var mockMvc: MockMvcRequestSpecification

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = mockMvc(TestHomeController(), restDocumentation, listOf(customAuthenticationFilter))
    }

    @Test
    fun `사용자 로그인`() {
        // given, when
        val login = mapOf("username" to "홍길동", "password" to "password")
        val response = mockMvc
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(jacksonObjectMapper().writeValueAsString(login))
            .post("/auth/login")

        // then
        response.then()
            .assertThat()
            .status(HttpStatus.OK)
            .body("accessToken", notNullValue())
            .body("refreshToken", notNullValue())

        // doc
        response.makeDocument("사용자 로그인") {
            requestBody(
                "username" type STRING means "사용자 아이디",
                "password" type STRING means "사용자 비밀번호",
            )
            responseBody(
                "accessToken" type STRING means "API 요청 시 함께 보내야하는 토큰",
                "refreshToken" type STRING means "토큰 재발급을 위한 토큰",
            )
        }
    }
}

@RestController("/")
class TestHomeController {
    @GetMapping
    fun home(): ResponseEntity<String> = ResponseEntity.ok("ok")
}

class LoginSuccessHandlerStub : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val loginToken = testTokenProvider.generateLoginSuccessToken(authentication.toCreateTokenRequest())
        val body = LoginTokenResponse(loginToken)
        response.apply {
            status = HttpStatus.OK.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            testObjectMapper.writeValue(outputStream, body)
        }
    }

    private fun Authentication.toCreateTokenRequest(): CreateTokenRequest =
        CreateTokenRequest(name, authorities.map { it.authority })
}
