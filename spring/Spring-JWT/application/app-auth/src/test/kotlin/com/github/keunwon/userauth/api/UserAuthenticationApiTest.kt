package com.github.keunwon.userauth.api

import com.github.keunwon.appauth.api.UserAuthenticationApi
import com.github.keunwon.appauth.api.UserSignRequest
import com.github.keunwon.restdocs.RestDocsSupport
import com.github.keunwon.restdocs.makeDocument
import com.github.keunwon.restdocs.paramMeans
import com.github.keunwon.user.AUTHENTICATION_VALID_CODE_CODE
import com.github.keunwon.user.USER_EMAIL
import com.github.keunwon.user.USER_NAME
import com.github.keunwon.user.USER_NICKNAME
import com.github.keunwon.user.USER_PASSWORD
import com.github.keunwon.user.service.UserRegisterService
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

class UserAuthenticationApiTest : RestDocsSupport() {
    private val userRegisterService = mockk<UserRegisterService>()
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup2() {
        mockMvc = mockMvc(UserAuthenticationApi(userRegisterService))
    }

    @Test
    fun `인증코드를 생성합니다`() {
        every { userRegisterService.generateAuthenticationCode(any()) } returns "CODE"

        mockMvc.post("/auth/authentication-code") {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            param("email", "test@test.com")
        }.andExpect {
            status { isCreated() }
        }.makeDocument("회원 인증코드 생성") {
            requestParameters(
                "email" paramMeans "회원가입 대상 이메일",
            )
        }
    }

    @Test
    fun `인증코드 인증합니다`() {
        every { userRegisterService.authenticateEmail(any(), any()) } just Runs

        mockMvc.post("/auth/authenticate-email") {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            param("email", "test@test.com")
            param("code", "VALID_CODE")
        }.andExpect {
            status { isNoContent() }
        }.makeDocument("인증코드 검증") {
            requestParameters(
                "email" paramMeans "인증코드 생성 시 사용했던 이메일",
                "code" paramMeans "인증코드",
            )
        }
    }

    @Test
    fun `사용자 회원가입을 한다`() {
        val userSignRequest = UserSignRequest(
            email = USER_EMAIL,
            name = USER_NAME,
            nickname = USER_NICKNAME,
            password = USER_PASSWORD,
            confirmPassword = USER_PASSWORD,
            authenticationCode = AUTHENTICATION_VALID_CODE_CODE,
        )
        every { userRegisterService.register(any()) } just Runs

        mockMvc.post("/auth/sign") {
            contentType = MediaType.APPLICATION_JSON
            content = userSignRequest.toJson()
        }.andExpect {
            status { isCreated() }
        }
    }
}
