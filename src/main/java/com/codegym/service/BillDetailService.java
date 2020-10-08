package com.codegym.service;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillDetailService {
    Page<BillDetail> findAllByBillId(Long id, Pageable pageable);

    void save(BillDetail billDetail);

    BillDetail editById(Long id);

    void deleteById(Long id);


}
