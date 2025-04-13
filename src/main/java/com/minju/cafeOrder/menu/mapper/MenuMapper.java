package com.minju.cafeOrder.menu.mapper;

import com.minju.cafeOrder.menu.dto.Category;
import com.minju.cafeOrder.menu.dto.Menu;
import com.minju.cafeOrder.menu.dto.MenuResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
//    Page<Menu> getAllMenus(Pageable pageable);
    List<Menu> getAllMenus(@Param("size") int size, @Param("offset") int offset, @Param("category")Category category);

}
