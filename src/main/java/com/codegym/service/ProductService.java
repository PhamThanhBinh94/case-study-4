package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    void save(Product product);

    Product findById(String id);

    void deleteById(String id);

    List<Product> findFirst6ByType(String type);


    Page<Product> findAllByType(String type, Pageable pageable);

    Page<Product> findAllByTypeOrIdOrOrBrand(String type,String id,String brand,Pageable pageable);

//    List<Product> findAllByTypeOrIdOrOrBrand(String type,String id,String brand);
    Page<Product> findByNameContaining(String q, Pageable pageable);
}
