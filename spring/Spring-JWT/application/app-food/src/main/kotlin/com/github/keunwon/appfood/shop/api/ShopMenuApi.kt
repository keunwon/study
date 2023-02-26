package com.github.keunwon.appfood.shop.api

import com.github.keunwon.core.support.LogSupport
import com.github.keunwon.food.shop.service.MenuBoard
import com.github.keunwon.food.shop.service.MenuRegistration
import com.github.keunwon.food.shop.service.ShopService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopMenuApi(
    private val shopService: ShopService,
) {
    @GetMapping("/api/shop/{shopId}/menu")
    fun getMenuBoard(@PathVariable shopId: Long): ResponseEntity<MenuBoard> {
        val menuBoard = shopService.getMenuBoard(shopId)
        return ResponseEntity.ok(menuBoard)
    }

    @PostMapping("/api/shop/menu")
    fun addMenu(@RequestBody request: MenuRegistration): ResponseEntity<Unit> {
        shopService.registryMenu(request)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    companion object : LogSupport
}
