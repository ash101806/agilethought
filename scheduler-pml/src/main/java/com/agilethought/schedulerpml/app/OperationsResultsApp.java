package com.agilethought.schedulerpml.app;

import java.util.List;

import com.agilethought.schedulerpml.dao.Transaction;
import com.agilethought.schedulerpml.dto.RiskOperationsIPDTO;
/**
 * Interface for definition of business layer to query analysis results
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
public interface OperationsResultsApp {
	/**
	 * Method to get information of risk operation gruped by IP Address
	 * @return List of operations with Risk
	 * @throws Exception query exceptions only
	 */
	public List<RiskOperationsIPDTO> getRiskOperationByIp() throws Exception;
	/**
	 * Method to get all transaction of specfic country
	 * @param countryCode ISO COUNTRY CODE
	 * @return List of operations of country
	 * @throws Exception query exceptions only
	 */
	public List<Transaction> getTransactionsByCountry(String countryCode) throws Exception;
}
