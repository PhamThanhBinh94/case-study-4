package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> search(String q) {
        return customerRepository.findByNameContaining(q);
    }

    @Override
    public Customer findById(String phone) {
        return customerRepository.findById(phone).get();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(String phone) {
        customerRepository.deleteById(phone);
    }
}
