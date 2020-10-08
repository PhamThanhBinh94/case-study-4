package com.codegym.repository;

import com.codegym.model.Bill;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
    Bill findBillByBillId(Long id);

    Page<Bill> findAllByBillIdOrCustomerIdOrStatus(Long billId,String customerId ,String status, Pageable pageable);

    Page<Bill> findAllByBillId(Long id, Pageable pageable);
}
