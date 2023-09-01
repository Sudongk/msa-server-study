package com.example.pay.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Menu {

    private Long id;

    private String name;

    private Integer price;

    private Long storeId;

}
