package com.minju.cafeOrder.cart.service;

import com.minju.cafeOrder.cart.dto.Cart;
import com.minju.cafeOrder.cart.dto.CartMenuDto;
import com.minju.cafeOrder.cart.dto.CartRequest;
import com.minju.cafeOrder.cart.dto.CartResponse;
import com.minju.cafeOrder.cart.mapper.CartMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartMapper cartMapper;


    @Override
    public CartResponse.CartAddResponse insertCart(CartRequest.CartAddRequest request, int userId) {
        Cart cart = Cart.builder()
                .userId(userId)
                .menuId(request.menuId())
                .quantity(request.quantity())
                .build();

        int cnt = cartMapper.countCart(cart);

        int updateFlg;

        if(cnt > 0) {
            try {
                updateFlg = cartMapper.updateCart(cart);
            } catch (Exception e) {
                log.error("장바구니 추가 중 DB 오류 발생", e);
                throw new RuntimeException("장바구니 추가 실패");
            }

        } else {
            try {
                updateFlg = cartMapper.insertCart(cart);
            } catch (Exception e) {
                log.error("장바구니 추가 중 DB 오류 발생", e);
                throw new RuntimeException("장바구니 추가 실패");
            }
        }


        return CartResponse.CartAddResponse.from(updateFlg);
    }

    @Override
    public List<CartMenuDto> getCart(int userId) {

        return cartMapper.getCartByUserId(userId);
    }
}
