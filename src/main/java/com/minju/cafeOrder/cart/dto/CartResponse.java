package com.minju.cafeOrder.cart.dto;

public class CartResponse {

    public record CartAddResponse(
            int cardId
    ) {
        public static CartAddResponse from(int updateFlg) {
            return new CartAddResponse(
                    updateFlg
            );
        }
    }
}
