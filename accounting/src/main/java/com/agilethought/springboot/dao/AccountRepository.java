package com.agilethought.springboot.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Optional<Account> findByAccountNumber(String accountNumber);
}
