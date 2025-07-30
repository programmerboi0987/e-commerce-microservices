package com.pcshop.cartservice.controller;

import com.pcshop.cartservice.dto.AddToCartRequest;
import com.pcshop.cartservice.model.CartItem;
import com.pcshop.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(Authentication authentication) {
        return ResponseEntity.ok(service.getCartItems(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody AddToCartRequest request,
                                              Authentication authentication) {
        return ResponseEntity.ok(service.addToCart(authentication.getName(), request));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable Long itemId) {
        service.removeItem(itemId);
        return ResponseEntity.ok("Item removed");
    }

    @DeleteMapping
    public ResponseEntity<?> clearCart(Authentication authentication) {
        service.clearCart(authentication.getName());
        return ResponseEntity.ok("Cart cleared");
    }
}
