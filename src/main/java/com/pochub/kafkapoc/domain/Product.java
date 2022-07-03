package com.pochub.kafkapoc.domain;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String title;
    private String description;
    private double price;

}
