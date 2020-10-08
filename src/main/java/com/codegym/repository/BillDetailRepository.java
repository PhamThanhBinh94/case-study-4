package com.codegym.repository;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends PagingAndSortingRepository<BillDetail, Long> {
  Page<BillDetail> findBillDetailsByBillId(Long id, Pageable pageable);
}
