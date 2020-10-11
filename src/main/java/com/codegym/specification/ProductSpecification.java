//package com.codegym.specification;
//
//import com.codegym.model.Product;
//import org.springframework.data.jpa.domain.Specification;
//
//public final class ProductSpecification {
//
//    public static Specification<Product> hasBrand(String brand){
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.BRAND),brand));
//    }
//
//}
