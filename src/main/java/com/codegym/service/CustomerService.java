package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    List<Customer> search(String q);

    Customer findById(String phone);

    void save(Customer contact);

    void delete(String phone);
}
