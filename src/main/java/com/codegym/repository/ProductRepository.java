package com.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.codegym.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
    List<Product> findFirst6ByType(String type);

    Page<Product> findAllByType(String type, Pageable pageable);
}
