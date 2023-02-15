package com.keunwon.auth.api

import com.keunwon.auth.JwtAuthenticationManagerStub
import com.keunwon.auth.RestDocsSupport
import com.keunwon.auth.STRING
import com.keunwon.auth.TestHomeController
import com.keunwon.auth.createClaimsInfo
import com.keunwon.auth.domain.USER_EMAIL
import com.keunwon.auth.makeDocument
import com.keunwon.auth.security.jwt.JwtLoginAuthenticationFilter
import com.keunwon.auth.security.jwt.LoginTokenResponse
import com.keunwon.auth.testObjectMapper
import com.keunwon.auth.testTokenProvider
import com.keunwon.auth.toJson
import com.keunwon.auth.type
import io.restassured.module.mockmvc.kotlin.extensions.Extract
import io.restassured.module.mockmvc.kotlin.extensions.Given
import io.restassured.module.mockmvc.kotlin.extensions.Then
import io.restassured.module.mockmvc.kotlin.extensions.When
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.test.web.servlet.MockMvc
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ExtendWith(RestDocumentationExtension::class)
class UserLoginApiTest : RestDocsSupport() {
    private val authenticationManager = JwtAuthenticationManagerStub
    private val customAuthenticationFilter =
        JwtLoginAuthenticationFilter(authenticationManager, testObjectMapper).apply {
            setAuthenticationSuccessHandler(LoginSuccessHandlerStub())
        }

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = createMockMvc(TestHomeController, restDocumentation, listOf(customAuthenticationFilter))
    }

    @Test
    fun `사용자 로그인`() {
        // given, when
        val request = mapOf("username" to "홍길동", "password" to "password")

        Given {
            mockMvc(mockMvc)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body(toJson(request))
        } When {
            post("/auth/login")
        } Then {
            status(HttpStatus.OK)
            body("accessToken", notNullValue())
            body("refreshToken", notNullValue())
        } Extract {
            response().makeDocument("사용자 로그인") {
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
}

class LoginSuccessHandlerStub : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val claimsInfo = createClaimsInfo(subject = USER_EMAIL)
        val loginToken = testTokenProvider.generateLoginSuccessToken(claimsInfo)
        val body = LoginTokenResponse(loginToken)
        response.apply {
            status = HttpStatus.OK.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            testObjectMapper.writeValue(outputStream, body)
        }
    }
}