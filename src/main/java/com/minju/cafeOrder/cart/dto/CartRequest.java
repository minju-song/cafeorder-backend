package com.minju.cafeOrder.cart.dto;

public class CartRequest {

    public record CartAddRequest(
            int menuId,
            int quantity
    ) {
        public static CartAddRequest from(Cart cart) {
            return new CartAddRequest(
            cart.getMenuId(),
            cart.getQuantity()
            );
        }
    }
}
