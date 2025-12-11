package com.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.bean.Account;
import com.learn.dao.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public void save(Account account) {
		accountRepository.save(account);
	}

	public void delete(Long id) {
		accountRepository.deleteById(id);
	}

	public Optional<Account> findByID(Long id) {
		return accountRepository.findById(id);
	}
	
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
	
	public List<Account> findByStatus(String status) {
		return accountRepository.findByStatus(status);
	}
}