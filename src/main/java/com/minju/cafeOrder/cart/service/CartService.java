package com.minju.cafeOrder.cart.service;

import com.minju.cafeOrder.cart.dto.CartMenuDto;
import com.minju.cafeOrder.cart.dto.CartRequest;
import com.minju.cafeOrder.cart.dto.CartResponse;

import java.util.List;

public interface CartService {

    CartResponse.CartAddResponse insertCart(CartRequest.CartAddRequest request, int userId);

    List<CartMenuDto> getCart(int userId);
}
