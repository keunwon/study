package com.keunwon.jwt.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.NUMBER
import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.STRING
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.type
import io.mockk.every
import io.mockk.mockk
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.hamcrest.Matchers
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
        every { userService.sign(userSignDto) } returns User(
            name = "홍길동",
            username = userSignDto.username,
            password = userSignDto.password,
            nickname = userSignDto.nickname,
            role = UserRole.USER,
            id = 1L,
        )

        val response = mockMvc
            .contentType(ContentType.JSON)
            .body(jacksonObjectMapper().writeValueAsString(userSignDto))
            .post("/auth/sign")

        response.prettyPrint()

        // then
        response.then()
            .status(HttpStatus.OK)
            .body("id", Matchers.notNullValue())
            .body("username", Matchers.equalTo(userSignDto.username))
            .body("nickname", Matchers.equalTo(userSignDto.nickname))

        // doc
        response.makeDocument("사용자 회원가입") {
            requestBody(
                "username" type STRING means "사용자 아이디",
                "password" type STRING means "사용자 비밀번호",
                "nickname" type STRING means "닉네임",
                "name" type STRING means "사용자 이름",
            )
            responseBody(
                "id" type NUMBER means "서버에서 사용하는 사용자 아이디",
                "username" type STRING means "생성된 사용자가 아이디",
                "nickname" type STRING means "생성된 사용자 닉네임",
            )
        }
    }

    companion object {
        val userSignDto = UserSignDto(
            name = "홍길동",
            username = "test-id",
            password = "password",
            nickname = "닉네임",
        )
    }
}
