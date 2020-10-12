package com.codegym.service;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillService {
    Page<Bill> findAll(Pageable pageable);

    void save(Bill bill);

    Bill editById(Long id);

    void deleteById(Long id);

    Bill findByBillId(Long id);

    Page<Bill> findAllByBillId(Long id, Pageable pageable);

    Page<Bill> findAllByCustomerIdOrderByDateDesc(String id, Pageable pageable);
}
