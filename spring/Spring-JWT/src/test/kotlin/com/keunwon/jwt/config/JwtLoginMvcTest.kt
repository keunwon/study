package com.keunwon.jwt.config

import com.keunwon.jwt.LOGIN_PASSWORD
import com.keunwon.jwt.LOGIN_USERNAME
import com.keunwon.jwt.USERNAME
import com.keunwon.jwt.USER_PASSWORD
import com.keunwon.jwt.USER_WRONG_PASSWORD
import com.keunwon.jwt.createUser
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.getByUsername
import com.keunwon.jwt.toJson
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
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
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
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `사용자 로그인 성공 시 정상(200) 응답`() {
        // given
        val username = USERNAME
        val password = USER_PASSWORD
        val request = mapOf("username" to username, "password" to password)
        userRepository.save(createUser(password = passwordEncoder.encode(password)))

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
        val request = mapOf("username" to "", "password" to LOGIN_PASSWORD)

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 명이 비어있거나 존재하지 않습니다."
        }
    }

    @Test
    fun `사용자 비밀번호 없이 로그인 시 실패(401) 응답`() {
        // given
        val request = mapOf("username" to LOGIN_USERNAME, "password" to "")

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 비밀번호가 비어있거나 존재하지 않습니다."
        }
    }

    @Test
    fun `사용자가 존재하지 않는 경우 실패(401) 응답`() {
        // given
        val request = mapOf("username" to "wrong-id", "password" to USER_PASSWORD)

        // when, then
        thenUnauthorizedResult(request) {
            "$USERNAME 찾을 수 없습니다"
        }
    }

    @Test
    fun `사용자 비밀번호가 다른 경우 실패(401) 응답`() {
        // given
        val request = mapOf("username" to USERNAME, "password" to USER_WRONG_PASSWORD)
        userRepository.save(createUser())

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 계정이 잠겨있습니다. 사용자: $USERNAME"
        }
    }

    @Test
    fun `사용자 계정이 잠긴 경우 실패(401) 응답`() {
        // given
        val request = mapOf("username" to USERNAME, "password" to USER_PASSWORD)
        userRepository.save(createUser(isAccountNonLocked = false))

        // when, then
        thenUnauthorizedResult(request) {
            "사용자 계정이 잠겨있습니다. 사용자: $USERNAME"
        }
    }

    @Test
    fun `사용자 로그인 실패 횟수가 10회 이상인 경우, 계정이 잠김`() {
        // given
        val request = mapOf("username" to USERNAME, "password" to USER_WRONG_PASSWORD)
        userRepository.save(createUser(failedPasswordCount = User.MAX_PASSWORD_FAILURED_COUNT))

        // when, then
        thenUnauthorizedResult(request) {
            "$USERNAME 비밀번호가 일치하지 않습니다"
        }
        with(userRepository.getByUsername(USERNAME)) {
            assertAll({
                assertThat(isAccountNonLocked).isFalse
                assertThat(failedPasswordCount).isEqualTo(User.MAX_PASSWORD_FAILURED_COUNT + 1)
            })
        }
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
