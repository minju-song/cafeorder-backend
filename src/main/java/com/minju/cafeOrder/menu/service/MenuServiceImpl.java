package com.minju.cafeOrder.menu.service;

import com.minju.cafeOrder.menu.dto.Category;
import com.minju.cafeOrder.menu.dto.Menu;
import com.minju.cafeOrder.menu.dto.MenuResponse;
import com.minju.cafeOrder.menu.mapper.MenuMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{

    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public Page<MenuResponse.MenuDto> getMenus(int size, int page, Category category) {
        int offset = page * size;

        List<MenuResponse.MenuDto> menus = menuMapper.getAllMenus(size, offset, category).stream()
                .map(MenuResponse.MenuDto::from)
                .collect(Collectors.toList());
        return new PageImpl<>(menus, PageRequest.of(page, size), menus.size());
    }
}
