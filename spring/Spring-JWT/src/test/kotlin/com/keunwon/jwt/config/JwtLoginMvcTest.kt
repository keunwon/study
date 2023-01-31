package com.keunwon.jwt.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class JwtLoginMvcTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `사용자 로그인 성공 시 정상(200) 응답`() {
        // given
        val login = mapOf("username" to username, "password" to userPassword)
        userRepository.save(simpleUser())

        // when, then
        mockMvc.perform(
            post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
            status().isOk,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.accessToken", notNullValue()),
            jsonPath("$.refreshToken", notNullValue()),
        ).andDo(
            document(
                "사용자 로그인-1",
                requestFields(
                    fieldWithPath("username").description("사용자 아이디"),
                    fieldWithPath("password").description("사용자 비밀번호"),
                ),
                responseFields(
                    fieldWithPath("accessToken").description("accessToken"),
                    fieldWithPath("refreshToken").description("refreshToken"),
                    //fieldWithPath("expirationAccessToken").description("accessToken 유효기간"),
                    //fieldWithPath("expirationRefreshToken").description("refreshToken 유효기간")
                )
            )
        )
    }

    @Test
    fun `사용자 명 없이 로그인 시 실패(401) 응답`() {
        // given
        val login = mapOf("username" to "", "password" to userPassword)

        // when, then
        mockMvc.perform(
            post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
            status().isUnauthorized,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value()),
            jsonPath("$.message").value("사용자 명이 비어있거나 존재하지 않습니다."),
        )
    }

    @Test
    fun `사용자 비밀번호 없이 로그인 시 실패(401) 응답`() {
        // given
        val login = mapOf("username" to username, "password" to "")

        // when, then
        mockMvc.perform(
            post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
            status().isUnauthorized,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value()),
            jsonPath("$.message").value("사용자 비밀번호가 비어있거나 존재하지 않습니다.")
        )
    }

    @Test
    fun `사용자가 존재하지 않는 경우 실패(401) 응답`() {
        // given
        val login = mapOf("username" to username, "password" to userPassword)

        // when, then
        mockMvc.perform(
            post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
            status().isUnauthorized,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value("401"),
            jsonPath("$.message").value("존재하지 않는 사용자입니다. 사용자: $username")
        )
    }

    @Test
    fun `사용자 비밀번호가 다른 경우 실패(401) 응답`() {
        // given
        val login = mapOf("username" to username, "password" to failUserPassword)
        userRepository.save(simpleUser())

        // when, then
        mockMvc.perform(
            post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
            status().isUnauthorized,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value("401"),
            jsonPath("$.message").value("사용자 비밀번호가 일치하지 않습니다. 사용자: $username")
        )
    }

    @Test
    fun `사용자 계정이 잠긴 경우 실패(401) 응답`() {
        // given
        val login = mapOf("username" to username, "password" to userPassword)
        userRepository.save(simpleUser().apply { isActivated = false })

        // when, then
        mockMvc.perform(
            post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
            status().isUnauthorized,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value("401"),
            jsonPath("$.message").value("사용자 계정이 잠겨있습니다. 사용자: $username")
        )
    }

    @Test
    fun `사용자 로그인 실패 횟수가 10회 이상인 경우, 계정이 잠김`() {
        // given
        val login = mapOf("username" to username, "password" to failUserPassword)
        userRepository.save(simpleUser().apply { failCount = 10 })

        // when, then
        mockMvc.perform(
            post(loginUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpectAll(
            status().isUnauthorized,
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$.code").value("401"),
            jsonPath("$.message").value("사용자 비밀번호가 일치하지 않습니다. 사용자: $username")
        )

        val user = userRepository.findByUsername(username)!!
        assertAll({
            assertThat(user.isActivated).isFalse
            assertThat(user.failCount).isEqualTo(11)
        })
    }

    companion object {
        private val passwordEncoder = BCryptPasswordEncoder()
        private const val loginUrl = "/auth/login"
        private const val username = "test-id"
        private const val userPassword = "password"
        private const val failUserPassword = "failPassword"
        private val objectMapper = jacksonObjectMapper()

        private fun simpleUser() = User(
            name = "홍길동",
            username = username,
            password = passwordEncoder.encode(userPassword),
            nickname = "닉네임",
            isActivated = true,
            role = UserRole.USER
        )
    }
}
