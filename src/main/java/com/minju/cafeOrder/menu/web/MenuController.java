package com.minju.cafeOrder.menu.web;

import com.minju.cafeOrder.menu.dto.Category;
import com.minju.cafeOrder.menu.dto.MenuResponse;
import com.minju.cafeOrder.menu.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public Page<MenuResponse.MenuDto> getMenus(@RequestParam int size, @RequestParam int page, @RequestParam(required = false) Optional<Category> category) {
        return menuService.getMenus(size, page, category.orElse(null));
    }
}
