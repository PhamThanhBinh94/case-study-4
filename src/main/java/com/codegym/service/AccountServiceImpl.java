package com.codegym.service;

import com.codegym.model.Account;
import com.codegym.model.Role;
import com.codegym.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account findUsersById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void remove(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        List<Role> roles = new ArrayList<>();
        roles.add(account.getRole());
        User user = new User(account.getUsername(), account.getPassword(),roles);
        return user;
    }
}
