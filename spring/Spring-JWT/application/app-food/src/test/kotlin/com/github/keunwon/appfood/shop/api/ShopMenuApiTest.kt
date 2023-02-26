package com.github.keunwon.appfood.shop.api

import com.github.keunwon.food.shop.MenuBuilder
import com.github.keunwon.food.shop.ShopBuilder
import com.github.keunwon.food.shop.service.MenuBoard
import com.github.keunwon.food.shop.service.ShopService
import com.github.keunwon.restdocs.RestDocsSupport
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

class ShopMenuApiTest : RestDocsSupport() {
    private val shopService = mockk<ShopService>()
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = mockMvc(
            controller = ShopMenuApi(shopService),
        )
    }

    @Test
    fun `가게 메뉴를 조회한다`() {
        val shopId = 1L
        every { shopService.getMenuBoard(shopId) } returns MenuBoard(
            shop = ShopBuilder().build(),
            menus = listOf(MenuBuilder().build())
        )

        mockMvc.get("/api/shop/menu/$shopId")
            .andExpect {
                status { isOk() }
                jsonPath("$.shopId", notNullValue())
            }
    }
}
