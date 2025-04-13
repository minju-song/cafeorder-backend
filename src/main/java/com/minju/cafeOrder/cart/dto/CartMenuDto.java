package com.minju.cafeOrder.cart.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartMenuDto {
    private int cartId;
    private int quantity;
    private int menuId;
    private String menuName;
    private String menuCategory;
    private int menuPrice;
    private String menuImage;
}
