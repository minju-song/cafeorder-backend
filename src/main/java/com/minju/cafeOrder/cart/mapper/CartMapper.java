package com.minju.cafeOrder.cart.mapper;

import com.minju.cafeOrder.cart.dto.Cart;
import com.minju.cafeOrder.cart.dto.CartMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    int insertCart(Cart cart);

    int countCart(Cart cart);

    int updateCart(Cart cart);

    List<CartMenuDto> getCartByUserId(int userId);
}
