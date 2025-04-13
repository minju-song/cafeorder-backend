package com.minju.cafeOrder.cart.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private int id;
    private int userId;
    private int menuId;
    private int quantity;
}
