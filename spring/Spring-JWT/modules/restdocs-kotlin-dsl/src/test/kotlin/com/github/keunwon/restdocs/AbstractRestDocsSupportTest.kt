package com.github.keunwon.restdocs

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class RestDocsSupportTest : AbstractRestDocsSupport() {
    private val controller = TestHomeController()

    @Test
    fun `요청 json, 응답 json restdocs 생성`() {
        mockMvc(controller).post("/json-body") {
            contentType = MediaType.APPLICATION_JSON
            content = JsonBody(1L, "username", "password").toJson()
        }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
            jsonPath("$.username", Matchers.notNullValue())
            jsonPath("$.password", Matchers.notNullValue())
        }.andDo {
            print()
        }.makeDocument("json-body") {
            requestBody(
                "code" type NUMBER means "요청 id 값",
                "username" type STRING means "요청 username 값",
                "password" type STRING means "요청 password 값"
            )
            responseBody(
                "code" type NUMBER means "응답 id 값",
                "username" type STRING means "응답 username 값",
                "password" type STRING means "응답 password 값",
            )
        }
    }

    @Test
    fun `queryString 사용하여 restdocs 생성`() {
        mockMvc(controller).get("/query-string") {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            param("username", "usernameParam")
            param("password", "passwordParam")
        }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
            jsonPath("$.username", Matchers.notNullValue())
            jsonPath("$.password", Matchers.notNullValue())
        }.makeDocument("queryString") {
            requestParameters(
                "username" paramMeans "username param",
                "password" paramMeans "password param",
            )
            responseBody(
                "username" type STRING means "username param value",
                "password" type STRING means "password param value",
            )
        }
    }
}
