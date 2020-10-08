package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findFirst6ByType(String type) {
        return productRepository.findFirst6ByType(type);
    }

    @Override
    public Page<Product> findAllByType(String type, Pageable pageable) {
        return productRepository.findAllByType(type,pageable);
    }

    @Override
    public Page<Product> findAllByTypeOrIdOrOrBrand(String type, String id, String brand, Pageable pageable) {
        return productRepository.findAllByTypeOrIdOrBrand(type, id, brand, pageable);
    }
//
//    @Override
//    public List<Product> findAllByTypeOrIdOrOrBrand(String type, String id, String brand) {
//        return productRepository.findAllByTypeOrIdOrBrand(type, id, brand);
//    }
}
