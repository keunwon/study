package com.github.keunwon.restdocs

import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class RestDocsSupportTest : AbstractRestDocsSupport() {
    private val controller = TestHomeController()

    @Test
    fun `응답코드 200, 응답 json 확인`() {
        val response = mockMvc(controller)
            .get("/")

        response.then()
            .statusCode(HttpStatus.OK.value())
            .makeDocument("응답 json") {
                responseBody(
                    "id" type STRING means "id 값",
                    "password" type STRING means "password 값",
                )
            }
    }

    @Test
    fun `응답코드 200, 요청 json, 응답 json`() {
        val response = mockMvc(controller)
            .body(JsonBody(1L, "username", "password").toJson())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .post("/json-body")

        response.then()
            .statusCode(HttpStatus.OK.value())
            .contentType(ContentType.JSON)
            .makeDocument("json-body") {
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

}
