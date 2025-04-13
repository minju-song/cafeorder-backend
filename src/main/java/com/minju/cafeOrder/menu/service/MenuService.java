package com.minju.cafeOrder.menu.service;

import com.minju.cafeOrder.menu.dto.Category;
import com.minju.cafeOrder.menu.dto.MenuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {
    Page<MenuResponse.MenuDto> getMenus(int size, int page, Category category);
}
