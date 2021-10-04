package com.keunwon.springboot;

import com.keunwon.springboot.service.KitchenService;
import reactor.core.publisher.Flux;

public class DishMetaphor {

    static class PoliteServer {
        private final KitchenService kitchenService;

        public PoliteServer(KitchenService kitchenService) {
            this.kitchenService = kitchenService;
        }

        public Flux<Dish> doingMyJob() {
            return kitchenService.getDishes()
                    .doOnNext(dish -> System.out.println("Tank you for" + dish + "!"))
                    .doOnError(error -> System.out.println("So sorry about" + error.getMessage()))
                    .doOnComplete(() -> System.out.println("Tanks for all your hard work!"))
                    .map(Dish::deliver);
        }
    }

    static class PoliteRestaurant {
        public static void main(String[] args) {
            PoliteServer server = new PoliteServer(new KitchenService());

            server.doingMyJob().subscribe(
                    dish -> System.out.println("Consuming" + dish),
                    System.out::println
            );
        }
    }
}
