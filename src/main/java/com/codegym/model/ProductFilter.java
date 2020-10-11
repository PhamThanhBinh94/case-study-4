package com.codegym.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductFilter {
    private Double minPrice;
    private Double maxPrice;
    private List<String> brands;
    private String sortBy;
}
