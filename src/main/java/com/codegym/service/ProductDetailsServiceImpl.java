package com.codegym.service;

import com.codegym.model.ProductDetails;
import com.codegym.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService{

    @Autowired
    private ProductDetailsRepository repository;

    @Override
    public ProductDetails findDetailById(String id) {
        return repository.findById(id).orElse(null);
    }
}
