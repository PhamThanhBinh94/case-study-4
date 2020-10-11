package com.codegym.repository;

import com.codegym.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
    Bill findBillByBillId(Long id);

    Page<Bill> findAllByBillIdOrCustomerIdOrStatus(Long billId,String customerId ,String status, Pageable pageable);

    Page<Bill> findAllByBillId(Long id, Pageable pageable);

//    Page<Bill> sortBillByBillId(Long id, Pageable pageable);
//
//    Page<Bill> sortBillByDateOrStatus(Date date, String Status, Pageable pageable);
}
