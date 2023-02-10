package com.keunwon.auth.config

import com.keunwon.auth.LOGIN_PASSWORD
import com.keunwon.auth.LOGIN_USERNAME
import com.keunwon.auth.domain.LoginPolicyBuilder
import com.keunwon.auth.domain.PasswordBuilder
import com.keunwon.auth.domain.USER_EMAIL
import com.keunwon.auth.domain.USER_PASSWORD
import com.keunwon.auth.domain.UserBuilder
import com.keunwon.auth.domain.WRONG_USER_PASSWORD
import com.keunwon.auth.domain.user.LoginPolicy
import com.keunwon.auth.domain.user.UserRepository
import com.keunwon.auth.domain.user.getByEmail
import com.keunwon.auth.toJson
import org.assertj.core.api.Assertions.assertThat
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
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.transaction.annotation.Transactional

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class JwtLoginMvcTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `사용자 로그인 성공 시 정상(200) 응답`() {
        // given
        val request = mapOf(
            "username" to USER_EMAIL,
            "password" to USER_PASSWORD,
        )
        userRepository.save(
            UserBuilder(
                id = 1L,
                password = PasswordBuilder(passwordEncoder.encode(USER_PASSWORD)).build(),
            ).build()
        )

        // when, then
        mockMvc.post(LOGIN_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = toJson(request)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.accessToken").isNotEmpty
            jsonPath("$.refreshToken").isNotEmpty
        }.andDo {
            handle(
                document(
                    "사용자 로그인(ver. 통합테스트)",
                    requestFields(
                        fieldWithPath("username").description("사용자 아이디"),
                        fieldWithPath("password").description("사용자 비밀번호"),
                    ),
                    responseFields(
                        fieldWithPath("accessToken").description("accessToken"),
                        fieldWithPath("refreshToken").description("refreshToken"),
                    )
                )
            )
        }
    }

    @Test
    fun `사용자 명 없이 로그인 시 실패(401) 응답`() {
        // given
        val request = mapOf(
            "username" to "",
            "password" to LOGIN_PASSWORD,
        )

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 명이 비어있거나 존재하지 않습니다."
        }
    }

    @Test
    fun `사용자 비밀번호 없이 로그인 시 실패(401) 응답`() {
        // given
        val request = mapOf(
            "username" to LOGIN_USERNAME,
            "password" to "",
        )

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 비밀번호가 비어있거나 존재하지 않습니다."
        }
    }

    @Test
    fun `사용자가 존재하지 않는 경우 실패(401) 응답`() {
        // given
        val request = mapOf(
            "username" to "wrong-id",
            "password" to USER_PASSWORD,
        )

        // when, then
        thenUnauthorizedResult(request) {
            "$USER_EMAIL 찾을 수 없습니다"
        }
    }

    @Test
    fun `사용자 비밀번호가 다른 경우 실패(401) 응답`() {
        // given
        val request = mapOf(
            "username" to USER_EMAIL,
            "password" to WRONG_USER_PASSWORD,
        )
        userRepository.save(UserBuilder().build())

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 계정이 잠겨있습니다. 이메일: $USER_EMAIL"
        }
    }

    @Test
    fun `사용자 계정이 잠긴 경우 실패(401) 응답`() {
        // given
        val request = mapOf(
            "username" to USER_EMAIL,
            "password" to USER_PASSWORD,
        )
        val loginPolicy = LoginPolicyBuilder(LoginPolicy.MAX_PASSWORD_FAILURED_COUNT).build()

        userRepository.save(
            UserBuilder(id = 1L, loginPolicy = loginPolicy).build()
        )

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 계정이 잠겨있습니다. 이메일: $USER_EMAIL"
        }
    }

    @Test
    fun `사용자 로그인 실패 횟수가 10회 이상인 경우, 계정이 잠김`() {
        // given
        val email = USER_EMAIL
        val request = mapOf(
            "username" to email,
            "password" to WRONG_USER_PASSWORD,
        )
        val loginPolicy = LoginPolicyBuilder(failedPasswordCount = LoginPolicy.MAX_PASSWORD_FAILURED_COUNT).build()
        userRepository.save(
            UserBuilder(loginPolicy = loginPolicy).build()
        )

        // when, then
        thenUnauthorizedResult(request) { "$email 비밀번호가 일치하지 않습니다" }
        val user = userRepository.getByEmail(email)
        assertAll({
            assertThat(user.loginPolicy.isAccountNotLocked).isFalse
            assertThat(user.loginPolicy.failedPasswordCount).isEqualTo(LoginPolicy.MAX_PASSWORD_FAILURED_COUNT)
        })
    }

    private fun thenUnauthorizedResult(login: Map<String, String>, message: () -> String) {
        mockMvc.post(LOGIN_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = toJson(login)
        }.andExpect {
            status { isUnauthorized() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.code").value(HttpStatus.UNAUTHORIZED.value())
            jsonPath("$.message").value(message())
        }
    }

    companion object {
        private const val LOGIN_URL = "/auth/login"
    }
}
