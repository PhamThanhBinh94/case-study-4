package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    void save(Product product);

    Product findById(String id);

    void deleteById(String id);
}
