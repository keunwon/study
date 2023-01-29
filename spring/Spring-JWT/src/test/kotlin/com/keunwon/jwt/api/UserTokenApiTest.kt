package com.keunwon.jwt.api

import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.STRING
import com.keunwon.jwt.TokenProviderFixture.testTokenProvider
import com.keunwon.jwt.domain.UserRole
import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.security.jwt.TokenIssue
import com.keunwon.jwt.toJson
import com.keunwon.jwt.type
import io.mockk.every
import io.mockk.mockkClass
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@ExtendWith(RestDocumentationExtension::class)
class UserTokenApiTest : RestDocsSupport() {
    private lateinit var mockMvc: MockMvcRequestSpecification
    private lateinit var tokenIssue: TokenIssue
    private val userTokenService = mockkClass(UserTokenService::class)

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = mockMvc(UserTokenApi(userTokenService), restDocumentation)
        tokenIssue = testTokenProvider.generateLoginSuccessToken(authentication)
    }

    @Test
    fun `refreshToken 이용하여 accessToken 새로 발급합니다`() {
        // given
        every { userTokenService.refreshAccessToken(any()) } returns AccessToken(
            tokenIssue.accessToken, testTokenProvider.getExpirationLocalDateTime(tokenIssue.accessToken))
        val response = mockMvc
            .contentType(MediaType.APPLICATION_JSON)
            .body(toJson(AccessTokenIssue("test-id", tokenIssue.refreshToken)))
            .post("/auth/refreshToken")
            .also { it.prettyPrint() }

        // when, then
        response.then()
            .status(HttpStatus.OK)
            .body("accessToken", Matchers.notNullValue())
            .body("expirationAccessToken", Matchers.notNullValue())

        response.makeDocument("accessToken 재발급") {
            requestBody(
                "username" type STRING means "사용자 아이디",
                "refreshToken" type STRING means "로그인 시 발급 받은 refreshToken",
            )
            responseBody(
                "accessToken" type STRING means "새로 발급한 accessToken",
                "expirationAccessToken" type STRING means "발급 된 accessToken 유효만료 기간",
            )
        }
    }

    @Test
    fun `username 값이 비어있으면 오류 응답을 반환합니다`() {
        val response = mockMvc
            .contentType(ContentType.JSON)
            .body(toJson(AccessTokenIssue("", tokenIssue.refreshToken)))
            .post("/auth/refreshToken")
            .also { it.prettyPrint() }

        response.then()
            .status(HttpStatus.BAD_REQUEST)
            .contentType(ContentType.JSON)
            .body("code", Matchers.equalTo(400))
            .body("message", Matchers.equalTo("파라미터가 유효하지 않습니다"))
            .body("errors", Matchers.equalTo(listOf("username 필수 파라미터 입니다")))
    }

    @Test
    fun `refreshToken 값이 비어있으면 오류 응답을 반환합니다`() {
        val response = mockMvc
            .contentType(ContentType.JSON)
            .body(toJson(AccessTokenIssue("test-id", "")))
            .post("/auth/refreshToken")
            .also { it.prettyPrint() }

        response.then()
            .status(HttpStatus.BAD_REQUEST)
            .contentType(ContentType.JSON)
            .body("code", Matchers.equalTo(400))
            .body("message", Matchers.equalTo("파라미터가 유효하지 않습니다"))
            .body("errors", Matchers.equalTo(listOf("refreshToken 필수 파라미터 입니다")))
    }

    companion object {
        val authentication = UsernamePasswordAuthenticationToken(
            "test-id", "password", generatedGrantedAuthorityList(UserRole.USER))
    }
}
