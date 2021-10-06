package com.keunwon.springboot.controller;

import com.keunwon.springboot.Cart;
import com.keunwon.springboot.Item;
import com.keunwon.springboot.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    private final InventoryService inventoryService;

    public HomeController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(value = "/")
    public Mono<Rendering> home() {
        return Mono.just(Rendering.view("home")
                .modelAttribute("items", inventoryService.getInventory())
                .modelAttribute("cart", inventoryService.getCart("My Cart")
                        .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }

    @PostMapping(value = "/add/{id}")
    public Mono<String> addToCart(@PathVariable String id) {
        return inventoryService.addItemToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

    @GetMapping(value = "/search")
    public Mono<Rendering> search(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String description,
                                  @RequestParam boolean useAnd) {
        return Mono.just(Rendering.view("home")
                .modelAttribute("results", inventoryService.searchByExample(name, description, useAnd))
                .build());
    }

    @PostMapping(value = "/")
    public Mono<String> createItem(@ModelAttribute Item newItem) {
        return inventoryService.saveItem(newItem)
                .thenReturn("redirect:/");
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<String> deleteItem(@PathVariable String id) {
        return inventoryService.deleteItem(id)
                .thenReturn("redirect:/");
    }
}
