package com.codegym.repository;

import com.codegym.model.ProductDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends CrudRepository<ProductDetails, String> {
}
