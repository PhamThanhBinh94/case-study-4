package com.codegym.repository;

import com.codegym.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Account findByUsername(String username);
}
