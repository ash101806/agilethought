package com.agilethought.schedulerpml.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
/**
 * Interface for reporitory of {@link Transaction}
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
public interface TransactionrepositoryDAO extends CrudRepository<Transaction, String> {
	@Query(value = "from Transaction t where upper(t.ipCountry) = :countryCode ")
	public List<Transaction> getTransactionCountry(String countryCode);
}
