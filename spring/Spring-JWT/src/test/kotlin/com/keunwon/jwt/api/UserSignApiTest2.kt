package com.keunwon.jwt.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.STRING
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.security.jwt.JwtLoginAuthenticationFilter
import com.keunwon.jwt.testObjectMapper
import com.keunwon.jwt.type
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(RestDocumentationExtension::class, MockKExtension::class)
class UserSignApiTest2 {
    lateinit var mockMvc: MockMvc

    private val userService: UserService = mockkClass(UserService::class)

    private val authentication = JwtAuthenticationManagerStub()
    private val customAuthenticationFilter = JwtLoginAuthenticationFilter(authentication, testObjectMapper).apply {
        setAuthenticationSuccessHandler(LoginSuccessHandlerStub())
    }

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = MockMvcBuilders.standaloneSetup(UserSignApi(userService))
            .apply<StandaloneMockMvcBuilder>(
                documentationConfiguration(restDocumentation)
            )
            .addFilter<StandaloneMockMvcBuilder>(customAuthenticationFilter)
            .build()
    }

    @Test
    fun `사용자 로그인`() {
        val login = mapOf("username" to "홍길동", "password" to "password")

        val response = given()
            .contentType(ContentType.JSON)
            .body(jacksonObjectMapper().writeValueAsString(login))
            .mockMvc(mockMvc)
            .`when`()
            .post("/auth/login")

        response.then()
            .status(HttpStatus.OK)
            .body("accessToken", notNullValue())
            .body("refreshToken", notNullValue())

        response.makeDocument("사용자 로그인-3") {
            requestBody(
                "username" type STRING means "사용자 아이디",
                "password" type STRING means "사용자 비밀번호",
            )
            responseBody(
                "accessToken" type STRING means "access-token",
                "refreshToken" type STRING means "refresh-token",
                //"expirationAccessToken" type STRING means "access-token 유효시간",
                //"expirationRefreshToken" type STRING means "refresh-token 유효시간"
            )
        }
    }
}
