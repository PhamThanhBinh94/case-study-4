package com.codegym.service;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BillDetailService {
    Page<BillDetail> findAllByBillId(Long id, Pageable pageable);

    List<BillDetail> findAllByBillId(Long id);

    void save(BillDetail billDetail);

    BillDetail editById(Long id);

    void deleteById(Long id);


}
