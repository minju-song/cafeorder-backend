package com.minju.cafeOrder.cart.web;

import com.minju.cafeOrder.cart.dto.CartMenuDto;
import com.minju.cafeOrder.cart.dto.CartRequest;
import com.minju.cafeOrder.cart.dto.CartResponse;
import com.minju.cafeOrder.cart.service.CartService;
import com.minju.cafeOrder.core.jwt.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public CartResponse.CartAddResponse insertCart(@RequestBody CartRequest.CartAddRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        System.out.println("요청>>"+request);
        int userId = userDetails.getId();
        return cartService.insertCart(request, userId);
    }

    @GetMapping
    public List<CartMenuDto> getCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return cartService.getCart(userDetails.getId());
    }
}
