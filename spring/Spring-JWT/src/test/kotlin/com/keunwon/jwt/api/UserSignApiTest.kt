package com.keunwon.jwt.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.STRING
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.type
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension

@ExtendWith(RestDocumentationExtension::class)
class UserSignApiTest : RestDocsSupport() {
    private lateinit var mockMvc: MockMvcRequestSpecification
    private val userService = mockk<UserService>()

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = mockMvc(UserSignApi(userService), restDocumentation)
    }

    @Test
    fun `사용자 회원가입`() {
        every { userService.sign(userSignDto) } just Runs

        val response = mockMvc
            .contentType(ContentType.JSON)
            .body(jacksonObjectMapper().writeValueAsString(userSignDto))
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

    companion object {
        val userSignDto = UserSignRequest(
            name = "홍길동",
            username = "test-id",
            password = "password",
            nickname = "닉네임",
        )
    }
}
