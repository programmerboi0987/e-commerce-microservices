package com.pcshop.cartservice.service;

import com.pcshop.cartservice.dto.AddToCartRequest;
import com.pcshop.cartservice.model.CartItem;
import com.pcshop.cartservice.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository repository;

    public CartService(CartItemRepository repository) {
        this.repository = repository;
    }

    public List<CartItem> getCartItems(String userId) {
        return repository.findByUserId(userId);
    }

    public CartItem addToCart(String userId, AddToCartRequest request) {
        CartItem item = new CartItem();
        item.setUserId(userId);
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());
        return repository.save(item);
    }

    public void removeItem(Long itemId) {
        repository.deleteById(itemId);
    }

    @Transactional
    public void clearCart(String userId) {
        repository.deleteByUserId(userId);
    }
}
