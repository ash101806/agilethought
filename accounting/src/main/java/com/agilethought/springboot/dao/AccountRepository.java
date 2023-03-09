package com.agilethought.springboot.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
/**
 * Repository to manager the entity {@link Account}
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public interface AccountRepository extends CrudRepository<Account, String> {
	Optional<Account> findByAccountNumber(String accountNumber);
}
