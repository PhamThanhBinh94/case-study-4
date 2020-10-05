package com.codegym.repository;

import org.springframework.stereotype.Repository;
import com.codegym.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
}
