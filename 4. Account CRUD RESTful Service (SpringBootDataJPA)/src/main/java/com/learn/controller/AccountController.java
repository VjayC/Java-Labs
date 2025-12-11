package com.learn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.bean.Account;
import com.learn.exception.UserNotFoundException;
import com.learn.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public List<Account> getAccounts(){
		return accountService.findAll();
	}

	@GetMapping(value ="/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable(name = "id") Long id){
		Optional<Account> account = accountService.findByID(id);
		if (account.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(account.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		accountService.save(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(account);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) throws UserNotFoundException {
		if(accountService.findByID(account.getId()).isPresent()) {
			accountService.save(account);
			return ResponseEntity.status(HttpStatus.OK).body(account);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Account> deleteAccount(@RequestParam Long id) throws UserNotFoundException {
		if(accountService.findByID(id).isPresent()) {
			accountService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<Account> findByUsername(@PathVariable String username){
		Optional<Account> account = accountService.findByUsername(username);
		if (account.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(account.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<?> findByStatus(@PathVariable String status) {
		if (!"active".equalsIgnoreCase(status) && !"inactive".equalsIgnoreCase(status)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status. Allowed values are: active, inactive");
		}
		return ResponseEntity.status(HttpStatus.OK).body(accountService.findByStatus(status));
	}
}