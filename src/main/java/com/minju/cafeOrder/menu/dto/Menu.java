package com.minju.cafeOrder.menu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    int id;
    String name;
    String description;
    Category category;
    int price;
    String image;
}
