package com.codegym.repository;

import com.codegym.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
    Bill findBillByBillId(Long id);

    Page<Bill> findAllByBillId(Long id, Pageable pageable);

    Page<Bill> findAllByCustomerIdOrderByDateDesc(String id, Pageable pageable);

}
