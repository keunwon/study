package com.keunwon.jwt.api

import com.keunwon.jwt.InmemoryAuthenticationCodeRepository
import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.STRING
import com.keunwon.jwt.TestPasswordEncoder
import com.keunwon.jwt.USERNAME
import com.keunwon.jwt.USER_FULL_NAME
import com.keunwon.jwt.USER_NICKNAME
import com.keunwon.jwt.USER_PASSWORD
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.toJson
import com.keunwon.jwt.type
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension

@ExtendWith(RestDocumentationExtension::class)
class UserAuthenticationApiTest : RestDocsSupport() {
    private lateinit var mockMvc: MockMvcRequestSpecification

    private val userAuthenticationService = UserAuthenticationService(
        InmemoryUserRepository(),
        InmemoryAuthenticationCodeRepository(),
        TestPasswordEncoder(),
    )

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = mockMvc(UserAuthenticationApi(userAuthenticationService), restDocumentation)
    }

    @Test
    fun `사용자 회원가입`() {
        // given
        val request = UserSignRequest(USERNAME, USER_PASSWORD, USER_FULL_NAME, USER_NICKNAME)
        val response = mockMvc
            .contentType(ContentType.JSON)
            .body(toJson(request))
            .post("/auth/sign")

        response.prettyPrint()

        // then
        response.then()
            .status(HttpStatus.NO_CONTENT)

        // doc
        response.makeDocument("사용자 회원가입") {
            requestBody(
                "username" type STRING means "사용자 아이디",
                "password" type STRING means "사용자 비밀번호",
                "nickname" type STRING means "닉네임",
                "name" type STRING means "사용자 이름",
            )
        }
    }
}
