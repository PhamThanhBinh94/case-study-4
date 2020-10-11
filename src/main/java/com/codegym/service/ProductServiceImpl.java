package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

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

    @Override
    public Page<Product> findByNameContaining(String q, Pageable pageable) {
        return productRepository.findByNameContaining(q,pageable);
    }

    @Override
    public List<Product> filterProduct(Double LowestPrice, Double HighestPrice, List<String> brands) {
        List<Product> productList = productRepository.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                if (HighestPrice > LowestPrice) {
                    p = cb.and(p, cb.between(root.get("price"),LowestPrice,HighestPrice));
                }
                if (Objects.nonNull(brands)){
                    p = cb.and(p, root.get("brand").in(brands));
                }
                cq.orderBy(cb.desc(root.get("id")), cb.asc(root.get("id")));
                return p;
            }
        });
        return productList;
    }
//
//    @Override
//    public List<Product> findAllByTypeOrIdOrOrBrand(String type, String id, String brand) {
//        return productRepository.findAllByTypeOrIdOrBrand(type, id, brand);
//    }
}
