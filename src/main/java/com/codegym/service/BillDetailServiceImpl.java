package com.codegym.service;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import com.codegym.repository.BillDetailRepository;
import com.codegym.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public Page<BillDetail> findAllByBillId(Long id, Pageable pageable) {
        return billDetailRepository.findBillDetailsByBillId(id, pageable);
    }

    @Override
    public List<BillDetail> findAllByBillId(Long id) {
        return billDetailRepository.findBillDetailsByBillId(id);
    }

    @Override
    public void save(BillDetail billDetail) {
        billDetailRepository.save(billDetail);
    }

    @Override
    public BillDetail editById(Long id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        billDetailRepository.deleteById(id);
    }


}
