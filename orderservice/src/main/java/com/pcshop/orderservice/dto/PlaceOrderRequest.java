package com.pcshop.orderservice.dto;

import java.util.List;

public class PlaceOrderRequest {

    private List<Item> items;

    public static class Item {
        private Long productId;
        private int quantity;
        private double price;
        // getters/setters

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    // getters/setters

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

