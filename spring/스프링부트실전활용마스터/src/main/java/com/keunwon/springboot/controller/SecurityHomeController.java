package com.keunwon.springboot.controller;

import com.keunwon.springboot.Cart;
import com.keunwon.springboot.service.InventoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class SecurityHomeController {

    private final InventoryService inventoryService;

    public SecurityHomeController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    Mono<Rendering> home(Authentication auth) {
        return Mono.just(Rendering.view("home")
                .modelAttribute("items", inventoryService.getInventory())
                .modelAttribute("cart", inventoryService.getCart(cartName(auth))
                        .defaultIfEmpty(new Cart(cartName(auth))))
                .modelAttribute("auth", auth)
                .build());
    }

    @PostMapping(value = "/add/{id}")
    Mono<String> addToCart(Authentication auth, @PathVariable String id) {
        return inventoryService.addItemToCart(cartName(auth), id)
                .thenReturn("redirect:/");
    }

    Mono<Rendering> oauthHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                              @AuthenticationPrincipal OAuth2User oAuth2User) {
        return Mono.just(Rendering.view("home")
                .modelAttribute("items", inventoryService.getInventory())
                .modelAttribute("cart", inventoryService.getCart(cartName(oAuth2User))
                        .defaultIfEmpty(new Cart(cartName(oAuth2User))))
                .modelAttribute("userName", oAuth2User.getName())
                .modelAttribute("authorities", oAuth2User.getAuthorities())
                .modelAttribute("clientName", authorizedClient.getClientRegistration().getClientId())
                .modelAttribute("userAttributes", oAuth2User.getAuthorities())
                .build());
    }

    private static String cartName(OAuth2User oAuth2User) {
        return oAuth2User.getName() + "'s Cart";
    }

    private static String cartName(Authentication auth) {
        return auth.getName() + "'s Cart";
    }
}
