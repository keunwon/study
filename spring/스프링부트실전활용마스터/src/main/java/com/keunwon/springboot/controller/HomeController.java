package com.keunwon.springboot.controller;

import com.keunwon.springboot.Cart;
import com.keunwon.springboot.Item;
import com.keunwon.springboot.repository.CartRepository;
import com.keunwon.springboot.repository.ItemRepository;
import com.keunwon.springboot.service.CartService;
import com.keunwon.springboot.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final InventoryService inventoryService;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository, CartService cartService, InventoryService inventoryService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.inventoryService = inventoryService;
    }

    @GetMapping(value = "/")
    public Mono<Rendering> home() {
        return Mono.just(Rendering.view("home")
                .modelAttribute("items", itemRepository.findAll())
                .modelAttribute("cart", cartRepository.findById("My Cart")
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
        return itemRepository.save(newItem)
                .thenReturn("redirect:/");
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<String> deleteItem(@PathVariable String id) {
        return itemRepository.deleteById(id)
                .thenReturn("redirect:/");
    }
}
