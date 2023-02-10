package com.keunwon.auth.api

import com.keunwon.auth.InmemoryAuthenticationCodeRepository
import com.keunwon.auth.InmemoryUserPasswordHistoryRepository
import com.keunwon.auth.InmemoryUserRepository
import com.keunwon.auth.RestDocsSupport
import com.keunwon.auth.STRING
import com.keunwon.auth.TestPasswordEncoder
import com.keunwon.auth.VALID_TOKEN
import com.keunwon.auth.domain.UserBuilder
import com.keunwon.auth.domain.user.UserPasswordHistory
import com.keunwon.auth.makeDocument
import com.keunwon.auth.security.jwt.bearer
import com.keunwon.auth.toJson
import com.keunwon.auth.type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.patch

@ExtendWith(RestDocumentationExtension::class)
class UserAuthenticationApiTest2 : RestDocsSupport() {
    private lateinit var mockMvc: MockMvc

    private val userRepository = InmemoryUserRepository()
    private val authenticationRepository = InmemoryAuthenticationCodeRepository()
    private val userPasswordHistoryRepository = InmemoryUserPasswordHistoryRepository()
    private val passwordEncoder = TestPasswordEncoder

    private val userAuthenticationService = UserAuthenticationService(
        userRepository,
        authenticationRepository,
        userPasswordHistoryRepository,
        passwordEncoder,
    )

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = createAuthMockMvc(UserAuthenticationApi(userAuthenticationService), restDocumentation)
    }

    @Test
    fun `사용자 비밀번호를 변경합니다`() {
        val newPassword = "NEW_PASSWORD"
        val user = UserBuilder(id = 1L).build().also {
            userRepository.save(it)
            userPasswordHistoryRepository.save(UserPasswordHistory(it))
        }
        val request = EditPasswordRequest(user.password!!.value, newPassword)

        mockMvc.patch("/auth/me/edit-password") {
            bearer(VALID_TOKEN)
            contentType = MediaType.APPLICATION_JSON
            content = toJson(request)
        }.andExpect {
            status { isNoContent() }
        }.andDo {
            makeDocument("사용자 비밀번호 변경") {
                requestHeaders(
                    HttpHeaders.AUTHORIZATION to "accessToken",
                )
                requestBody(
                    "oldPassword" type STRING means "현재 비밀번호",
                    "newPassword" type STRING means "신규 비밀번호",
                )
            }
        }

        assertAll({
            assertTrue(passwordEncoder.matches(user.password!!.value, newPassword))
            assertThat(userPasswordHistoryRepository.findAllByUserId(user.id)).hasSize(2)
        })
    }
}
