package com.codegym.service;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import com.codegym.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public Bill editById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public Bill findByBillId(Long id) {
        return billRepository.findBillByBillId(id);
    }
}
