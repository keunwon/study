package com.keunwon.springboot.service;

import com.keunwon.springboot.Cart;
import com.keunwon.springboot.CartItem;
import com.keunwon.springboot.Item;
import com.keunwon.springboot.repository.CartRepository;
import com.keunwon.springboot.repository.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AltInventoryService {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public AltInventoryService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<Cart> getCart(String cartId) {
        return cartRepository.findById(cartId);
    }

    public Flux<Item> getInventory() {
        return itemRepository.findAll();
    }

    public Mono<Item> saveItem(Item newItem) {
        return itemRepository.save(newItem);
    }

    public Mono<Void> deleteItem(String id) {
        return itemRepository.deleteById(id);
    }

    public Mono<Cart> addItemToCart(String cartId, String itemId) {
        Cart myCart = cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .block();

        return myCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
                .findAny()
                .map(cartItem -> {
                    cartItem.increment();
                    return Mono.just(myCart);
                })
                .orElseGet(() ->
                        itemRepository.findById(itemId)
                                .map(CartItem::new)
                                .map(cartItem -> {
                                    myCart.getCartItems().add(cartItem);
                                    return myCart;
                                }))
                .flatMap(cartRepository::save);
    }
}
