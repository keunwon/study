package com.keunwon.jwt.api

import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.InmemoryUserTokenRepository
import com.keunwon.jwt.LOGIN_USERNAME
import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.STRING
import com.keunwon.jwt.common.UserRole
import com.keunwon.jwt.common.util.toLocalDateTime
import com.keunwon.jwt.domain.UserBuilder
import com.keunwon.jwt.domain.UserTokenBuilder
import com.keunwon.jwt.domain.user.UserRole
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.security.jwt.CreateTokenRequest
import com.keunwon.jwt.security.jwt.JwtLoginToken
import com.keunwon.jwt.testTokenProvider
import com.keunwon.jwt.toJson
import com.keunwon.jwt.type
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.kotlin.extensions.Extract
import io.restassured.module.mockmvc.kotlin.extensions.Given
import io.restassured.module.mockmvc.kotlin.extensions.Then
import io.restassured.module.mockmvc.kotlin.extensions.When
import io.restassured.module.mockmvc.response.MockMvcResponse
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(RestDocumentationExtension::class)
class UserTokenApiTest : RestDocsSupport() {
    private lateinit var mockMvc: MockMvc
    private lateinit var jwtLoginToken: JwtLoginToken

    private val userRepository = InmemoryUserRepository()
    private val userTokenRepository = InmemoryUserTokenRepository()
    private val userTokenService = UserTokenService(userRepository, userTokenRepository, testTokenProvider)

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = createMockMvc(UserTokenApi(userTokenService), restDocumentation)
        jwtLoginToken = testTokenProvider.generateLoginSuccessToken(
            CreateTokenRequest(username, UserRole.DEFAULT_ROLES)
        )
    }

    @Test
    fun `refreshToken 이용하여 accessToken 새로 발급합니다`() {
        val user = UserBuilder(id = 1L).build().also { userRepository.save(it) }
        val loginToken = testTokenProvider.generateLoginSuccessToken(
            CreateTokenRequest(user.username!!, listOf(user.role.name))
        )
        userTokenRepository.save(
            UserTokenBuilder(
                userId = 1L,
                refreshToken = loginToken.refreshToken.value,
                expirationRefreshToken = loginToken.refreshToken.expiredAt.toLocalDateTime(),
            ).build()
        )

        givenWhenMockMvc(username, jwtLoginToken.refreshToken.value) Then {
            status(HttpStatus.OK)
            body("value", notNullValue())
            body("expirationAccessToken", notNullValue())
        } Extract {
            response().makeDocument("accessToken 재발급") {
                requestBody(
                    "username" type STRING means "사용자 아이디",
                    "refreshToken" type STRING means "로그인 시 발급 받은 refreshToken",
                )
                responseBody(
                    "value" type STRING means "새로 발급한 accessToken",
                    "expirationAccessToken" type STRING means "발급 된 accessToken 유효만료 기간",
                )
            }
        }
    }

    @Test
    fun `username 값이 비어있으면 오류 응답을 반환합니다`() {
        givenWhenMockMvc("", jwtLoginToken.refreshToken.value) Then {
            status(HttpStatus.BAD_REQUEST)
            contentType(ContentType.JSON)
            body("code", equalTo(400))
            body("message", equalTo("파라미터가 유효하지 않습니다"))
            body("errors", equalTo(listOf("username 필수 파라미터 입니다")))
        }
    }

    @Test
    fun `refreshToken 값이 비어있으면 오류 응답을 반환합니다`() {
        givenWhenMockMvc(username, "") Then {
            status(HttpStatus.BAD_REQUEST)
            contentType(ContentType.JSON)
            body("code", equalTo(400))
            body("message", equalTo("파라미터가 유효하지 않습니다"))
            body("errors", equalTo(listOf("refreshToken 필수 파라미터 입니다")))
        }
    }

    private fun givenWhenMockMvc(username: String = LOGIN_USERNAME, refreshToken: String): MockMvcResponse {
        return Given {
            mockMvc(mockMvc)
            contentType(ContentType.JSON)
            body(toJson(AccessTokenIssueRequest(username, refreshToken)))
        } When {
            post("/auth/refreshToken")
        }
    }

    companion object {
        private const val username = LOGIN_USERNAME
    }
}
