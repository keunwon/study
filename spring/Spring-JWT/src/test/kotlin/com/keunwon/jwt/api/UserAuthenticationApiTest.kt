package com.keunwon.jwt.api

import com.keunwon.jwt.InmemoryAuthenticationCodeRepository
import com.keunwon.jwt.InmemoryUserPasswordHistoryRepository
import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.STRING
import com.keunwon.jwt.TestPasswordEncoder
import com.keunwon.jwt.domain.AuthenticationCodeBuilder
import com.keunwon.jwt.domain.USERNAME
import com.keunwon.jwt.domain.USER_EMAIL
import com.keunwon.jwt.domain.USER_FULL_NAME
import com.keunwon.jwt.domain.USER_NICKNAME
import com.keunwon.jwt.domain.USER_PASSWORD
import com.keunwon.jwt.domain.UserBuilder
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.param
import com.keunwon.jwt.toJson
import com.keunwon.jwt.type
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.kotlin.extensions.Extract
import io.restassured.module.mockmvc.kotlin.extensions.Given
import io.restassured.module.mockmvc.kotlin.extensions.Then
import io.restassured.module.mockmvc.kotlin.extensions.When
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(RestDocumentationExtension::class)
class UserAuthenticationApiTest : RestDocsSupport() {
    private lateinit var mockMvc: MockMvc

    private val userRepository = InmemoryUserRepository()
    private val authenticationRepository = InmemoryAuthenticationCodeRepository()
    private val userPasswordHistoryRepository = InmemoryUserPasswordHistoryRepository()

    private val userAuthenticationService = UserAuthenticationService(
        userRepository,
        authenticationRepository,
        userPasswordHistoryRepository,
        TestPasswordEncoder,
    )

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = createMockMvc(UserAuthenticationApi(userAuthenticationService), restDocumentation)
    }

    @Test
    fun `사용자 회원가입`() {
        val request = UserSignRequest(USERNAME, USER_PASSWORD, USER_EMAIL, USER_FULL_NAME, USER_NICKNAME)

        Given {
            mockMvc(mockMvc)
            contentType(ContentType.JSON)
            body(toJson(request))
        } When {
            post("/auth/sign")
        } Then {
            status(HttpStatus.CREATED)
        } Extract {
            response().makeDocument("사용자 회원가입") {
                requestBody(
                    "username" type STRING means "사용자 아이디",
                    "password" type STRING means "사용자 비밀번호",
                    "email" type STRING means "사용자 이메일 주소",
                    "nickname" type STRING means "닉네임",
                    "name" type STRING means "사용자 이름",
                )
            }
        }
    }

    @Test
    fun `인증코드를 생성합니다`() {
        val user = UserBuilder(id = 1L).build()
        userRepository.save(user)

        Given {
            mockMvc(mockMvc)
            param("email", user.email)
        } When {
            post("/auth/authentication-code")
        } Then {
            status(HttpStatus.NO_CONTENT)
        } Extract {
            response().makeDocument("인증 코드 발급") {
                requestParams(
                    "email" param "회원가입 시 입력했던 Email 주소",
                )
            }
        }
    }

    @Test
    fun `인증코드를 검증합니다`() {
        val authenticationCode = AuthenticationCodeBuilder().build()
        authenticationRepository.save(authenticationCode)

        Given {
            mockMvc(mockMvc)
            param("email", authenticationCode.email)
            param("code", authenticationCode.code)
        } When {
            post("/auth/authenticate-email")
        } Then {
            status(HttpStatus.NO_CONTENT)
        }
    }
}
