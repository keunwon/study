package com.keunwon.springboot.controller;

import com.keunwon.springboot.Dish;
import com.keunwon.springboot.service.KitchenService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller
public class ServerController {
    private final KitchenService kitchenService;

    public ServerController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> serverDishes() {
        return kitchenService.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> deliverDishes() {
        return kitchenService.getDishes().map(Dish::deliver);
    }
}
