package com.keunwon.jwt.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.STRING
import com.keunwon.jwt.TokenProviderFixture.testTokenProvider
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.security.jwt.CreateTokenRequest
import com.keunwon.jwt.security.jwt.JwtLoginAuthenticationFilter
import com.keunwon.jwt.security.jwt.LoginTokenResponse
import com.keunwon.jwt.testObjectMapper
import com.keunwon.jwt.type
import io.mockk.junit5.MockKExtension
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Controller
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.WebApplicationContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(RestDocumentationExtension::class, MockKExtension::class)
@WebMvcTest(
    excludeFilters = [
        ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [Controller::class, RestController::class])
    ]
)
@ContextConfiguration
class UserLoginApiTest {
    private val authentication = JwtAuthenticationManagerStub()
    private val customAuthenticationFilter = JwtLoginAuthenticationFilter(authentication, testObjectMapper).apply {
        setAuthenticationSuccessHandler(LoginSuccessHandlerStub())
    }

    lateinit var mockMvc: MockMvcRequestSpecification

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider, webApplicationContext: WebApplicationContext) {
        mockMvc = RestAssuredMockMvc.given()
            .mockMvc(
                MockMvcBuilders.webAppContextSetup(webApplicationContext)
                    .apply<DefaultMockMvcBuilder>(
                        documentationConfiguration(restDocumentation)
                            .operationPreprocessors()
                            .withRequestDefaults(Preprocessors.prettyPrint())
                            .withResponseDefaults(Preprocessors.prettyPrint())
                    )
                    .addFilter<DefaultMockMvcBuilder>(customAuthenticationFilter)
                    .build()
            )
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
        //.body("expirationAccessToken", notNullValue())
        //.body("expirationRefreshToken", notNullValue())

        // doc
        response.makeDocument("사용자 로그인") {
            requestBody(
                "username" type STRING means "사용자 아이디",
                "password" type STRING means "사용자 비밀번호",
            )
            responseBody(
                "accessToken" type STRING means "API 요청 시 함께 보내야하는 토큰",
                "refreshToken" type STRING means "토큰 재발급을 위한 토큰",
                //"expirationAccessToken" type STRING means "accessToken 유효시간",
                //"expirationRefreshToken" type STRING means "refreshToken 요효시간",
            )
        }
    }
}

class JwtAuthenticationManagerStub : AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        return UsernamePasswordAuthenticationToken.unauthenticated("홍길동", "password")
    }
}

class LoginSuccessHandlerStub : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val loginToken = testTokenProvider.generateLoginSuccessToken(authentication.toCreateTokenRequest())
        val body = LoginTokenResponse.from(loginToken)
        response.apply {
            status = HttpStatus.OK.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            testObjectMapper.writeValue(outputStream, body)
        }
    }

    private fun Authentication.toCreateTokenRequest(): CreateTokenRequest =
        CreateTokenRequest(name, authorities.map { it.authority })
}
