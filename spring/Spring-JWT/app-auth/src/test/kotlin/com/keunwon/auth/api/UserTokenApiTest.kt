package com.keunwon.auth.api

import com.keunwon.auth.InmemoryUserRepository
import com.keunwon.auth.InmemoryUserTokenRepository
import com.keunwon.auth.LOGIN_USERNAME
import com.keunwon.auth.RestDocsSupport
import com.keunwon.auth.STRING
import com.keunwon.auth.common.util.toLocalDateTime
import com.keunwon.auth.createClaimsInfo
import com.keunwon.auth.domain.USER_EMAIL
import com.keunwon.auth.domain.UserBuilder
import com.keunwon.auth.domain.UserTokenBuilder
import com.keunwon.auth.makeDocument
import com.keunwon.auth.security.jwt.JwtLoginToken
import com.keunwon.auth.testTokenProvider
import com.keunwon.auth.toJson
import com.keunwon.auth.type
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
        jwtLoginToken = testTokenProvider.generateLoginSuccessToken(createClaimsInfo(subject = USER_EMAIL))
    }

    @Test
    fun `refreshToken 이용하여 accessToken 새로 발급합니다`() {
        val user = UserBuilder(id = 1L).build()
        userRepository.save(user)
        userTokenRepository.save(
            UserTokenBuilder(
                userId = user.id,
                refreshToken = jwtLoginToken.refreshToken.value,
                expirationRefreshToken = jwtLoginToken.refreshToken.expiredAt.toLocalDateTime(),
            ).build()
        )

        givenWhenMockMvc(user.information.email, jwtLoginToken.refreshToken.value) Then {
            status(HttpStatus.OK)
            body("value", notNullValue())
            body("expirationAccessToken", notNullValue())
        } Extract {
            response().makeDocument("accessToken 재발급") {
                requestBody(
                    "email" type STRING means "사용자 아아디(이메일)",
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
    fun `email 값이 비어있으면 오류 응답을 반환합니다`() {
        givenWhenMockMvc("", jwtLoginToken.refreshToken.value) Then {
            status(HttpStatus.BAD_REQUEST)
            contentType(ContentType.JSON)
            body("code", equalTo(HttpStatus.BAD_REQUEST.value()))
            body("message", equalTo("파라미터가 유효하지 않습니다"))
            body("errors", equalTo(listOf("email 필수 파라미터 입니다")))
        }
    }

    @Test
    fun `refreshToken 값이 비어있으면 오류 응답을 반환합니다`() {
        givenWhenMockMvc(LOGIN_USERNAME, "") Then {
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
}
