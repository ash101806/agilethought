package com.agilethought.schedulerpml.procesor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import com.agilethought.schedulerpml.dao.Transaction;
/**
 * Class for transaction procesor, mainly to detect risk operations
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
public class TransactionItemProcessor implements ItemProcessor<Transaction, Transaction> {
	@Value("${agilethought.pld.params.max-amount}")
	private BigDecimal maxAmount;
	private List<String> blackCountries = Arrays.asList("KR","AF", "CY");
	@Override
	public Transaction process(Transaction transaction) throws Exception {
		if(transaction.getAmount().compareTo(maxAmount) <=0)
			return null;
		if(!blackCountries.contains(transaction.getIpCountry().toUpperCase()))
			return null;
		return transaction;
	}

}
